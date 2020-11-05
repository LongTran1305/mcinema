package com.projects4.mcinema.service;

import com.projects4.mcinema.model.FAQs;
import com.projects4.mcinema.repository.FAQsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FAQsService {
    @Autowired
    private FAQsRepository repo;

    public List<FAQs> listAll(){
        return repo.findAll();
    }

    public void save(FAQs faqs){
        repo.save(faqs);
    }

    public FAQs get(int faqid){
        return repo.findById(faqid).get();
    }

    public void delete(int id){
        repo.deleteById(id);
    }


}
