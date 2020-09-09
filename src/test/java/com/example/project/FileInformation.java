package com.example.project;

public class FileInformation {
	String filename;
	String base64Img;
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getBase64Img() {
		return base64Img;
	}
	public void setBase64Img(String base64Img) {
		this.base64Img = base64Img;
	}
	@Override
	public String toString() {
		return "FileInformation [filename=" + filename + ", base64Img=" + base64Img + "]";
	}
	
	
}

