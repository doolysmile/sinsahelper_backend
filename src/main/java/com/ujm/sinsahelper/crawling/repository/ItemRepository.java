package com.ujm.sinsahelper.crawling.repository;

import com.ujm.sinsahelper.crawling.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        // KCH merge 바꾸기
        if (item.getItemId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i",Item.class)
                .getResultList();
    }

    public List<Item> findByPreference(String mainCategory, String subCategory) {
        return em.createQuery("select i from Item i where i.mainCategory = :mainCategory and i.subCategory = :subCategory", Item.class)
                .setParameter("mainCategory", mainCategory)
                .setParameter("subCategory", subCategory)
                .getResultList();
    }


}
