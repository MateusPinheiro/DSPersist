package l03.controller;

import java.io.File;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import l03.classes.Dados;

public class JsonController {

	public static void main(String[] args) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Dados data = new Dados();
		data = mapper.readValue(new URL("https://dadosabertos.camara.leg.br/api/v2/deputados?ordem=ASC&ordenarPor=nome"), Dados.class);
		mapper.writeValue(new File("./src/main/resources/result.json"), data);
	}

}