package l03.controller;

public class TestController {
	
	public static void main(String[] args) {
		JsoupController jc = new JsoupController();
		SaxController sc = new SaxController();
		jc.CriarCSV();
		sc.GerarCSV();
		// No terminal do windows não funciona, assim sendo tive que improvisar
		XSDValidator.main(new String [] {"./src/main/resources/students.xsd","./src/main/resources/students.xml"});
	}
}
