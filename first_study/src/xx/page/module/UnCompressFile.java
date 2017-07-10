package xx.page.module;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;



public class UnCompressFile {
	private static UnCompressFile instance = new UnCompressFile();
	public UnCompressFile() {
	}
	public static UnCompressFile getInstance() {
		return instance;
	}

	/**
	 * ��ѹzip����rar�������ݵ�ָ����Ŀ¼�£����Դ������ļ����°������ļ��е����
	 *
	 * @param zipFilename
	 *            Ҫ��ѹ��zip����rar���ļ�
	 * @param outputDirectory
	 *            ��ѹ���ŵ�Ŀ¼
	 */
	public synchronized List<Map<String, String>> unzip(String zipFilename, String outputDirectory)
			throws IOException {
		List<Map<String, String>>list=new ArrayList<Map<String,String>>();
		File outFile = new File(outputDirectory);
		if (!outFile.exists()) {
			outFile.mkdirs();
		}
		ZipFile zipFile = new ZipFile(zipFilename);
		Enumeration en = zipFile.getEntries();
		ZipEntry zipEntry = null;
		while (en.hasMoreElements()) {
			zipEntry = (ZipEntry) en.nextElement();
			if (zipEntry.isDirectory()) {
				// mkdir directory
				String dirName = zipEntry.getName();
				//System.out.println("=dirName is:=" + dirName + "=end=");
				dirName = dirName.substring(0, dirName.length() - 1);
				File f = new File(outFile.getPath() + File.separator + dirName);
				f.mkdirs();
			} else {
				//unzip file
				String strFilePath = outFile.getPath() + File.separator
						+ zipEntry.getName();
				Map<String, String> map=new HashMap<String, String>();
				map.put("inputfile", strFilePath);
				list.add(map);
				System.out.println("strFilePath��"+strFilePath);
				File f = new File(strFilePath);
				//the codes remedified by can_do on 2010-07-02 =begin=
				// //////////begin//////////
				//�ж��ļ������ڵĻ����ʹ������ļ������ļ��е�Ŀ¼
				if (!f.exists()) {
					String[] arrFolderName = zipEntry.getName().split("//");
					String strRealFolder = "";
					for (int i = 0; i < (arrFolderName.length - 1); i++) {
						strRealFolder += arrFolderName[i] + File.separator;
					}
					strRealFolder = outFile.getPath() + File.separator
							+ strRealFolder;
					File tempDir = new File(strRealFolder);
					//�˴�ʹ��.mkdirs()��������������.mkdir()
					tempDir.mkdirs();
				}
				////////////end//////
				// the codes remedified by can_do on 2010-07-02 =end=
				f.createNewFile();
				InputStream in = zipFile.getInputStream(zipEntry);
				FileOutputStream out = new FileOutputStream(f);
				try {
					int c;
					byte[] by = new byte[BUFFEREDSIZE];
					while ((c = in.read(by)) != -1) {
						out.write(by, 0, c);
					}
					////out.flush();
				} catch (IOException e) {
					throw e;
				} finally {
					out.close();
					in.close();
				}
			}
		}
		return list;
	}
	private static final int BUFFEREDSIZE = 1024;
	public List<Map<String, String>> jieya(String inputfile,String outputfile) {
		UnCompressFile bean = new UnCompressFile();
		List<Map<String, String>> list=new ArrayList<Map<String,String>>();
		try {
			list=bean.unzip("H://�½��ļ���.zip", "H://input");

			////bean.unzip("d://struts.rar", "D://struts");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list ;
	}
}