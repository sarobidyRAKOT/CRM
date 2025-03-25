package site.easy.to.build.crm.repository.lead;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import site.easy.to.build.crm.entity.lead.Depense_lead;
import site.easy.to.build.crm.entity.lead.Montant_dl;


@Repository
public interface Montant_dlRepository extends JpaRepository <Montant_dl, Integer> {

    List<Montant_dl> findByDepenseLead (Depense_lead depenseLead);
    List<Montant_dl> findByDepenseLead (Depense_lead depenseLead, Sort sort);

}
