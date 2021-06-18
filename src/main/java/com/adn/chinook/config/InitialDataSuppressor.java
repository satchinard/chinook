package com.adn.chinook.config;

import com.adn.chinook.repository.UtilsRepository;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author cagecfi
 */
@Component
public class InitialDataSuppressor implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;
    @Autowired
    private UtilsRepository utilsRepository;
    @Autowired
    EntityManager em;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        //        utilsRepository.dropTable("BIOMETRIES");
        //        utilsRepository.dropTable("BIOMETRIES_DOIGT");
        alreadySetup = true;
    }

}
