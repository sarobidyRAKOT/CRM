
package site.easy.to.build.crm.service.customer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.easy.to.build.crm.entity.Customers.Taux_Alert;
import site.easy.to.build.crm.repository.Customer.Taux_AlertRepository;

@Service
public class Taux_AlertService {

    private final Taux_AlertRepository taux_AlertRepository;

    @Autowired
    public Taux_AlertService(Taux_AlertRepository taux_AlertRepository) {
        this.taux_AlertRepository = taux_AlertRepository;
    }

    public Taux_Alert find_maxDaty () {
        return taux_AlertRepository.findFirstByOrderByDatyDesc();
    }
    public Taux_Alert save(Taux_Alert taux_Alert) {
        return taux_AlertRepository.save (taux_Alert);
    }

    public Taux_Alert find_before (String daty) {
        Optional<Taux_Alert> taux = taux_AlertRepository.findTopByDatyLessThanEqualOrderByDatyDesc(daty);
        return taux.orElse(null); // Retourne `null` si aucun taux n'est trouvé
    }
}

