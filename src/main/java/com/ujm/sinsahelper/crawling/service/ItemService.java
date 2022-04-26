package com.ujm.sinsahelper.crawling.service;

import com.ujm.sinsahelper.crawling.domain.Item;
import com.ujm.sinsahelper.crawling.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    public List<Item> findItemByPreference(String mainCategory, String subCategory, Long deliverypreference, Long sizepreference, Long qualitypreference) {

        List<Item> items = itemRepository.findByPreference(mainCategory, subCategory);

        for(Item item : items) {
            Long score = item.getDeliveryScore() * deliverypreference + item.getSizeScore() * sizepreference + item.getQualityScore() * qualitypreference;
            item.setTotalScore(score);
        }

        Collections.sort(items, comparator);

        // KCH : 출력하는 갯수
        items = items.subList(0,2);

        return items;
    }
    
    // Item totalScore별 정렬 함수
    private Comparator<Item> comparator = new Comparator<Item>() {
        @Override
        public int compare(Item a, Item b) {
            return Math.toIntExact(b.getTotalScore()) - Math.toIntExact(a.getTotalScore());
        }
    };

}