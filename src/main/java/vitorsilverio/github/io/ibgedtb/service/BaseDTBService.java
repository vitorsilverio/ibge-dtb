package vitorsilverio.github.io.ibgedtb.service;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vitorsilverio.github.io.ibgedtb.entity.Estado;
import vitorsilverio.github.io.ibgedtb.entity.Municipio;
import vitorsilverio.github.io.ibgedtb.repository.EstadoRepository;
import vitorsilverio.github.io.ibgedtb.repository.MunicipioRepository;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

@Service
public class BaseDTBService {

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public void iniciarBancoDeDados() throws IOException {
        File base = baixarPacoteDTB();
        File planilha = deziparPlanilhaMunicipios(base);
        List<Municipio> municipios = gerarListaMunicipios(planilha);
        municipioRepository.saveAll(municipios);
    }

    private List<Municipio> gerarListaMunicipios(File planilha) throws IOException {
        List<Municipio> municipios = new ArrayList<>();
        HSSFSheet planilha1 = new HSSFWorkbook(new FileInputStream(planilha)).getSheetAt(0);
        Iterator<Row> iterator = planilha1.rowIterator();
        //Pular cabeçalho
        iterator.next();
        while (iterator.hasNext()) {
            Row linha = iterator.next();
            Municipio municipio = new Municipio();
            Estado estado = estadoRepository.findById(Integer.valueOf(linha.getCell(0).getStringCellValue())).get();
            municipio.setId(Long.valueOf(linha.getCell(7).getStringCellValue()));
            municipio.setNome(linha.getCell(8).getStringCellValue());
            municipio.setCodigoIBGE(municipio.getId());
            municipio.setEstado(estado);
            municipios.add(municipio);
        }
        return municipios;

    }

    private File baixarPacoteDTB() throws IOException {
        File base = File.createTempFile("DTB_2018", ".zip");
        FileUtils.copyURLToFile(new URL("ftp://geoftp.ibge.gov.br/organizacao_do_territorio/estrutura_territorial/divisao_territorial/2018/DTB_2018.zip"), base);
        return base;
    }

    private File deziparPlanilhaMunicipios(File base) throws IOException {
        byte[] buffer = new byte[1024];
        ZipInputStream zis =
                new ZipInputStream(new FileInputStream(base));
        while(!zis.getNextEntry().getName().equalsIgnoreCase("RELATORIO_DTB_BRASIL_MUNICIPIO.xls")){}
        File planilha = File.createTempFile("RELATORIO_DTB_BRASIL_MUNICIPIO",".xls");
        FileOutputStream fos = new FileOutputStream(planilha);
        int len;
        while ((len = zis.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
        }

        fos.close();
        return planilha;
    }
}
