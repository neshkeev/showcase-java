package com.github.neshkeev.showcase.jmx;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import javax.management.Attribute;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.List;
import java.util.Set;

public class JmxClientMain {
    public static void main(String[] args) throws Exception {
        final var vmd = getVMDescriptor();
        if (vmd == null) {
            return;
        }
        final VirtualMachine vm = VirtualMachine.attach(vmd);
        final var connectionString = vm.startLocalManagementAgent();
        final var url = new JMXServiceURL(connectionString);

        try (final var connector = JMXConnectorFactory.connect(url)) {
            final var connection = connector.getMBeanServerConnection();

            final Set<ObjectName> objectNames = connection.queryNames(null, null);
            for (ObjectName objectName : objectNames) {
                final var mBeanInfo = connection.getMBeanInfo(objectName);

                for (MBeanAttributeInfo attributeInfo : mBeanInfo.getAttributes()) {
                    if (!"HeapMemoryUsage".equals(attributeInfo.getName()) && !"NonHeapMemoryUsage".equals(attributeInfo.getName())) {
                        continue;
                    }

                    final var attr = getAttribute(objectName, attributeInfo, connection);

                    if (!(attr.getValue() instanceof CompositeData compositeData)) {
                        continue;
                    }

                    final long usedMemory = (long) compositeData.get("used");
                    final long maxMemory = (long) compositeData.get("max");

                    System.out.println(attributeInfo.getName());
                    System.out.println("\tUsed memory: " + usedMemory + " bytes");
                    System.out.println("\tMax memory: " + maxMemory + " bytes");
                }
            }
        }
    }

    private static Attribute getAttribute(ObjectName objectName, MBeanAttributeInfo attribute, MBeanServerConnection connection) throws Exception {
        final var attributeList = connection.getAttributes(objectName, new String[]{ attribute.getName() });
        return (Attribute) attributeList.get(0);
    }

    private static VirtualMachineDescriptor getVMDescriptor() {
        final long currentPid = ProcessHandle.current().pid();

        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : list) {
            if (!vmd.id().equals(Long.toString(currentPid))) {
                return vmd;
            }
        }
        return null;
    }
}