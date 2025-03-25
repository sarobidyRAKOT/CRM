package site.easy.to.build.crm.repository.lead;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import site.easy.to.build.crm.entity.lead.Lead_sup;


@Repository
public interface Lead_supRepository extends  JpaRepository <Lead_sup, Integer> {
    
}
