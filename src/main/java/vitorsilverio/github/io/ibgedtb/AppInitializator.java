package vitorsilverio.github.io.ibgedtb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vitorsilverio.github.io.ibgedtb.service.BaseDTBService;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class AppInitializator  {

    @Autowired
    private BaseDTBService service;

    @PostConstruct
    private void init() throws IOException {
        service.iniciarBancoDeDados();
    }
}
