package l03.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import l03.classes.Dados;

public class JsonController {

	private ObjectMapper mapper;

	public JsonController() {
		mapper = new ObjectMapper();
	}

	public void JaksonJson(String url) {
		Dados data = new Dados();
		try{
		data = mapper.readValue(new URL(url), Dados.class);
		mapper.writeValue(new File("./src/main/resources/result.json"), data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void serializeObjects(String caminho) throws IOException {
		Dados data = new Dados();
		data = mapper.readValue(new File(caminho), Dados.class);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./src/main/resources/dados.serialize"));
		oos.writeObject(data);
		oos.close();		
	}
	
	public void prettyJson(String caminho) throws IOException {
		Dados data = new Dados();
		data = mapper.readValue(new File(caminho), Dados.class);
		mapper.writerWithDefaultPrettyPrinter().writeValue(new File("./src/main/resources/dados_pretty.json"), data);
		data = mapper.readValue(new File("./src/main/resources/dados_pretty.json"), Dados.class);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./src/main/resources/dados_pretty.serialize"));
		oos.writeObject(data);
		oos.close();
	}

	public Dados deserializeObjectsFromFile() throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./src/main/resources/dados.serialize"));
		Dados data = (Dados) ois.readObject();
		return data;
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ObjectMapper mapper = new ObjectMapper();
		JsonController js = new JsonController();
		js.JaksonJson("https://dadosabertos.camara.leg.br/api/v2/deputados?ordem=ASC&ordenarPor=nome");
		js.serializeObjects("./src/main/resources/result.json");
		js.prettyJson("./src/main/resources/result.json");
		mapper.writeValue(new File("./src/main/resources/deserializado.json"), js.deserializeObjectsFromFile());
	}
}

