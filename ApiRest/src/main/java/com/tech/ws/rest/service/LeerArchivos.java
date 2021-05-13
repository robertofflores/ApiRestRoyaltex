package com.tech.ws.rest.service;


import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Formatter;
import java.util.Iterator;
 








import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LeerArchivos {
	public void leerArchivo() {
		String nombreArchivo = "inventario.xlsx";
		String rutaArchivo = "D:\\Desarrollo ECLIPSE\\Datos\\" + nombreArchivo;
	//	String hoja = "Hoja1";
		int anio,unidad;
		String mes,gestion,distribuidor,tipo,fecha,cliente,codigo,producto,prenda,marca,tallas,color,segmento;
		String precio,neto,iva,total;
 
		try (FileInputStream file = new FileInputStream(new File(rutaArchivo))) {
			// leer archivo excel
			XSSFWorkbook worbook = new XSSFWorkbook(file);
			//obtener la hoja que se va leer
			XSSFSheet sheet = worbook.getSheetAt(2);
			//obtener todas las filas de la hoja excel
			Iterator<Row> rowIterator = sheet.iterator();
 
			Row row;
			DataFormatter formatter = new DataFormatter();
			// se recorre cada fila hasta el final
			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				//se obtiene las celdas por fila
				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell;
				//se recorre cada celda
				
				while (cellIterator.hasNext()) {
					cell = cellIterator.next();
					
				}
				if(row.getRowNum()>=1){
				anio = (int)row.getCell(0).getNumericCellValue();
				mes = row.getCell(1).getStringCellValue();
				gestion = row.getCell(2).getStringCellValue();
				distribuidor = row.getCell(3).getStringCellValue();
				tipo = row.getCell(4).getStringCellValue();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
				fecha =  sdf.format(row.getCell(5).getDateCellValue());
				cliente = row.getCell(6).getStringCellValue();
				codigo = row.getCell(7).getStringCellValue();
				producto = row.getCell(8).getStringCellValue();
				prenda = row.getCell(9).getStringCellValue();
				marca = row.getCell(10).getStringCellValue();
				tallas = row.getCell(11).getStringCellValue();
				color = row.getCell(12).getStringCellValue();
				segmento = row.getCell(13).getStringCellValue();
				unidad = (int)row.getCell(14).getNumericCellValue();
				DecimalFormat df = new DecimalFormat("#.00");
				precio = df.format(row.getCell(15).getNumericCellValue());
				precio = precio.replace(",", ".");
				neto = df.format(row.getCell(16).getNumericCellValue());
				neto = neto.replace(",", ".");
				iva = df.format(row.getCell(17).getNumericCellValue());
				iva = iva.replace(",",".");
				total = df.format(row.getCell(18).getNumericCellValue());
				total = total.replace(",", ".");
				/*
				String distSql = "Insert into mbldadis (DADIS_COD_EMPR,DADIS_ANIO_DADIS,DADIS_MES_DADIS,DADIS_GEST_DADIS,DADIS_DIS_DADIS,"
						+ "DADIS_TIPO_DADIS,DADIS_FECH_DADIS,DADIS_CLIE_DADIS,DADIS_COD_PROD,DADIS_NOM_PROD,"
						+ "DADIS_PREND_DADIS,DADIS_MARC_DADIS,DADIS_TALLA_DADIS,DADIS_COLOR_DADIS,DADIS_SEGM_DADIS,"
						+ "DADIS_CANT_DADIS,DADIS_PREC_DADIS,DADIS_NETO_DADIS,DADIS_IVA_DADIS,DADIS_TOTA_DADIS) " 
						+ " values  "
						+ "(1,"+anio+",'"+mes+"','"+gestion+"','"+distribuidor+"','"+tipo+"','"+fecha+"','"+cliente+"','"
						+ codigo + "','"+producto+"','"+prenda+"','"+marca+"','"+tallas+"','"+color+"','"+segmento+"',"
						+ unidad +","+precio+","+neto+","+iva+","+total+")";
				System.out.println(distSql);*/
				/*String distSql ="Insert into mbldadis (dadis_cod_empr,dadis_anio_dadis,dadis_mes_dadis,dadis_gest_dadis,"
				+ "dadis_dis_dadis,dadis_tipo_dadis,dadis_clie_dadis,dadis_cod_prod,"
				+ "dadis_nom_prod,dadis_prend_dadis,dadis_marc_dadis,dadis_talla_dadis,dadis_color_dadis,"
				+ "dadis_segm_dadis,dadis_cant_dadis,dadis_prec_dadis,dadis_neto_dadis,dadis_iva_dadis,dadis_tota_dadis)"
				+ " values  (1,2017,'ENERO','VENTAS','LEE PORTOVIEJO','FACTURA','ROMERO ZAMBRANO JACK','L 01HD528XM11F109','SOPH','PANTALON','LEE','TALLA 10','STONE 1','MUJER',12,49.99,44.63,5.36,66.99)";	*/
				String distSql ="Insert into mbldadis (dadis_cod_empr)"
						+ " values  (1)";
				CargaDatos carga =new CargaDatos();
				if (carga.cargarDatos(distSql)){
					System.out.println("Éxito!");
				}
				}
			}
			worbook.close();
		} catch (Exception e) { 
			e.getMessage();
		}
		
		}
	}

