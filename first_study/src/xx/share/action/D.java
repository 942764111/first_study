/*
 *@(#)xx.share.action
 *@D.java.java  
 *@����ʱ��:2016-5-9����2:50:16
 *@���ߣ�������
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.share.action;

import java.util.Properties;

public class D{

public static void main (String args[]){
Properties props=System.getProperties(); //ϵͳ����

System.out.println("Java�����л����汾��"+props.getProperty("java.version"));
System.out.println("Java�����л�����Ӧ�̣�"+props.getProperty("java.vendor"));
System.out.println("Java��Ӧ�̵�URL��"+props.getProperty("java.vendor.url"));
System.out.println("Java�İ�װ·����"+props.getProperty("java.home"));
System.out.println("Java��������淶�汾��"+props.getProperty("java.vm.specification.version"));
System.out.println("Java��������淶��Ӧ�̣�"+props.getProperty("java.vm.specification.vendor"));
System.out.println("Java��������淶���ƣ�"+props.getProperty("java.vm.specification.name"));
System.out.println("Java�������ʵ�ְ汾��"+props.getProperty("java.vm.version"));
System.out.println("Java�������ʵ�ֹ�Ӧ�̣�"+props.getProperty("java.vm.vendor"));
System.out.println("Java�������ʵ�����ƣ�"+props.getProperty("java.vm.name"));
System.out.println("Java����ʱ�����淶�汾��"+props.getProperty("java.specification.version"));
System.out.println("Java����ʱ�����淶��Ӧ�̣�"+props.getProperty("java.specification.vender"));
System.out.println("Java����ʱ�����淶���ƣ�"+props.getProperty("java.specification.name"));
System.out.println("Java�����ʽ�汾�ţ�"+props.getProperty("java.class.version"));
System.out.println("Java����·����"+props.getProperty("java.class.path"));
System.out.println("���ؿ�ʱ������·���б���"+props.getProperty("java.library.path"));
System.out.println("Ĭ�ϵ���ʱ�ļ�·����"+props.getProperty("java.io.tmpdir"));
System.out.println("һ��������չĿ¼��·����"+props.getProperty("java.ext.dirs"));
System.out.println("����ϵͳ�����ƣ�"+props.getProperty("os.name"));
System.out.println("����ϵͳ�Ĺ��ܣ�"+props.getProperty("os.arch"));
System.out.println("����ϵͳ�İ汾��"+props.getProperty("os.version"));
System.out.println("�ļ��ָ�����"+props.getProperty("file.separator")); //�� unix ϵͳ���ǡ�����
System.out.println("·���ָ�����"+props.getProperty("path.separator")); //�� unix ϵͳ���ǡ�:��
System.out.println("�зָ�����"+props.getProperty("line.separator")); //�� unix ϵͳ���ǡ�/n��
System.out.println("�û����˻����ƣ�"+props.getProperty("user.name"));
System.out.println("�û�����Ŀ¼��"+props.getProperty("user.home"));
System.out.println("�û��ĵ�ǰ����Ŀ¼��"+props.getProperty("user.dir"));

}
}