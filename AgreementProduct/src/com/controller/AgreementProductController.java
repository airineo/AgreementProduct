package com.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.Agreement;

/**
 * 
 * @author Alberto
 *
 */
@RestController
@RequestMapping("/agreementProduct")
public class AgreementProductController {
	
	/**
	 * Controlador para la creación del archivo de Agreement
	 * @param agreement
	 * @return
	 */
	@PostMapping("/saveAgreement")
	public String saveAgreement(@RequestBody Agreement agreement) {
		Date date = new Date();
		SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MM-yyyy");
		String fileName = agreement.getName() +  simpleDate.format(date);
		String path = createFile(fileName, agreement);
		//Ésta respuesta se puede mejorar con un HttpResponseEntity y otros conceptos, sin embargo para efectos prácticos y de prueba se establece de ésta manera
		return path;
	}
	/**
	 * Método privado que se encarga de crear el archivo con la información recibida del acuerdo.
	 * @param fileName  Nombre del archivo con el cuál creará el archivo.
	 * @param agreement El objeto con la información para el contenido del archivo
	 * @return El path de dónde se almacenó el archivo txt
	 */
	private String createFile(String fileName, Agreement agreement) {

		String ruta = "C:"+ File.separator + "temp"+ File.separator + fileName + ".txt";
		File file = new File(ruta);

		try {
			file.createNewFile();
			FileWriter fw= new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("Name: " + agreement.getName());
			bw.newLine();
			bw.write("Signed by: " + agreement.getSignedBy());
			bw.newLine();
			bw.write("Products: " + agreement.getProducts());
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ruta;
	}
	/**
	 * Método que crea un objeto de acuerdo
	 * @param path Ruta de dónde se encuentra el archivo txt
	 * @return El objeto creado a partir del archivo txt
	 */
	@GetMapping("/createAgreement")
	public Agreement createAgreement(@RequestParam("path") String path) {

		File file = null;
		FileReader fr = null;
		BufferedReader br = null;
		Agreement agreement = new Agreement();
		try {
			file = new File (path);
			fr = new FileReader (file);
			br = new BufferedReader(fr);
			String linea;
			while((linea=br.readLine())!=null) {
				if(linea.contains("Name")) {
					agreement.setName(linea.substring(linea.indexOf(":")+1, linea.length()));
				}if(linea.contains("Signed")) {
					agreement.setSignedBy(linea.substring(linea.indexOf(":")+1, linea.length()));
				}if(linea.contains("Products")) {
					//					agreement.setName(linea.substring(linea.indexOf(":"), linea.length()-1));
				}
				System.out.println(linea);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				fr.close();
			} catch (IOException e) {
				System.out.println("No se pudo cerrar el archivo " + path);
				e.printStackTrace();
			}     
		}
		System.out.println("Agreement object created successfully");
		return agreement;
	}


}
