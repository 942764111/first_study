package com.easysearching.lucene.beans;

import java.io.File;

public class FileBean {

	private String pubWriter;//上传作者
	private String pubDate;//上传日期
	private File pubFile;
	private String viewPath;//文件预览路径
	private String zlid;//资料id
	private String keci;
	private String i_id;
	private String zsdmc;//知识点描述
	private File downLoadFile;//文件下载路径
	private String fileType;//文件类型
    private String TCName;//课程名称
    private String ZName ;//章名称
    private String JName;//节名称
	public File getDownLoadFile() {
		return downLoadFile;
	}

	public void setDownLoadFile(File downLoadFile) {
		this.downLoadFile = downLoadFile;
	}

	public String getZlid() {
		return zlid;
	}

	public void setZlid(String zlid) {
		this.zlid = zlid;
	}

	public String getKeci() {
		return keci;
	}

	public void setKeci(String keci) {
		this.keci = keci;
	}

	public String getI_id() {
		return i_id;
	}

	public void setI_id(String i_id) {
		this.i_id = i_id;
	}

	public File getPubFile() {
		return pubFile;
	}

	public void setPubFile(File pubFile) {
		this.pubFile = pubFile;
	}

	public String getPubWriter() {
		return pubWriter;
	}

	public void setPubWriter(String pubWriter) {
		this.pubWriter = pubWriter;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getViewPath() {
		return viewPath;
	}

	public void setViewPath(String viewPath) {
		this.viewPath = viewPath;
	}

	public String getZsdmc() {
		return zsdmc;
	}

	public void setZsdmc(String zsdmc) {
		this.zsdmc = zsdmc;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getTCName() {
		return TCName;
	}

	public void setTCName(String tCName) {
		TCName = tCName;
	}

	public String getZName() {
		return ZName;
	}

	public void setZName(String zName) {
		ZName = zName;
	}

	public String getJName() {
		return JName;
	}

	public void setJName(String jName) {
		JName = jName;
	}

	@Override
	public String toString() {
		return "FileBean [pubWriter=" + pubWriter + ", pubDate=" + pubDate
				+ ", pubFile=" + pubFile + ", viewPath=" + viewPath + ", zlid="
				+ zlid + ", keci=" + keci + ", i_id=" + i_id + ", zsdmc="
				+ zsdmc + ", downLoadFile=" + downLoadFile + ", fileType="
				+ fileType + ", TCName=" + TCName + ", ZName=" + ZName
				+ ", JName=" + JName + "]";
	}

}
