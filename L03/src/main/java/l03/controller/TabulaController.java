package l03.controller;

import java.io.File;
import java.io.PrintStream;

import org.apache.pdfbox.pdmodel.PDDocument;

import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.Table;
import technology.tabula.extractors.BasicExtractionAlgorithm;
import technology.tabula.writers.CSVWriter;

public class TabulaController {

	public void main() {
		ObjectExtractor oe = null;
		try {
			PDDocument document = PDDocument.load(new File("./src/main/resources/653_DiárioOficialTabeladeRemuneração2012.pdf"));
			oe = new ObjectExtractor(document);
			Page page = oe.extract(15);

			BasicExtractionAlgorithm bea = new BasicExtractionAlgorithm();
			Table table = bea.extract(page.getArea(810.0f,0.0f,580.0f,310.0f)).get(0);

			CSVWriter pp = new CSVWriter();
			pp.write(new PrintStream("./src/main/resources/arquivo.csv"), table);
			oe.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}