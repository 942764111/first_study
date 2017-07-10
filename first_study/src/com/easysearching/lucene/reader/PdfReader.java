package com.easysearching.lucene.reader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PdfReader extends FileReader {

	private File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public PdfReader(File file) {
		this.file = file;
	}

	public PdfReader() {

	}

	@Override
	public String getContent() {
		PDDocument pdfDocument = null;
		ByteArrayOutputStream byteArrayOut = null;
		OutputStreamWriter outputWriter = null;
		PDFTextStripper stripper = null;
		byte[] byteArrayContent = null;
		String content = null;
		try {
			
			System.out.println("≤‚ ‘:"+file);
			pdfDocument = PDDocument.load(file);
			byteArrayOut = new ByteArrayOutputStream();
			outputWriter = new OutputStreamWriter(byteArrayOut);
			stripper = new PDFTextStripper();
			stripper.writeText(pdfDocument, outputWriter);
			byteArrayContent = byteArrayOut.toByteArray();
			content = new String(byteArrayContent);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (outputWriter != null) {
					outputWriter.close();
				}
				if (byteArrayOut != null) {
					byteArrayOut.close();
				}
				if (pdfDocument != null) {
					pdfDocument.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return content;
	}

}
