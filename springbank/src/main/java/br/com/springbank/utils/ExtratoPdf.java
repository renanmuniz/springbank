package br.com.springbank.utils;

import br.com.springbank.modelo.Lancamento;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtratoPdf {

    public void gerarExtratoPdf(List<Lancamento> lancamentos) throws IOException, JRException {

        System.out.println("Início da geração de relatório");

        /* Output file location to create report in pdf form */
        //String outputFile = "C:\\Users\\renan\\Desktop\\" + "Extrato" + lancamentos.get(0).getConta().getNumero().toString() + ".pdf";
        String outputFile = "recursos/relatoriosJasper/gerados/" + "Extrato" + lancamentos.get(0).getConta().getNumero().toString() + ".pdf";

        /* Convert List to JRBeanCollectionDataSource */
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(lancamentos);

        /* Map to hold Jasper report Parameters */
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("CollectionBeanParam", itemsJRBean);

        //read jrxml file and creating jasperdesign object
        //InputStream input = new FileInputStream(new File("D:\\Estudo\\SpringBank\\springbank\\recursos\\relatoriosJasper\\Extrato.jrxml"));
        InputStream input = new FileInputStream(new File("recursos/relatoriosJasper/Extrato.jrxml"));


        JasperDesign jasperDesign = JRXmlLoader.load(input);

        /*compiling jrxml with help of JasperReport class*/
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        /* Using jasperReport object to generate PDF */
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        /*call jasper engine to display report in jasperviewer window*/
        //JasperViewer.viewReport(jasperPrint);


        /* outputStream to create PDF */
        OutputStream output = new FileOutputStream(outputFile);


        /* Write content to PDF file */
        JasperExportManager.exportReportToPdfStream(jasperPrint, output);

        System.out.println("Arquivo gerado: " + outputFile);

//        Path pdfPath = Paths.get(outputFile);
//        byte[] pdf = Files.readAllBytes(pdfPath);

        input.close();
        output.close();

//        return pdf;
    }

    public void enviarEmailExtratoPdf() {
        // TODO Falta fazer enviarEmailExtratoPdf()
    }


}
