package l03.controller;

public class TestController {
	
	public static void main(String[] args) {
		TabulaController tc = new TabulaController();
		XSDValidator xsd = new XSDValidator();
		JsoupController jc = new JsoupController();
		SaxController sc = new SaxController();
		// Classes de teste
//		jc.CriarCSV();
//		sc.GerarCSV();
		xsd.validar("./src/main/resources/students.xml");
//		tc.main();
	}
}
