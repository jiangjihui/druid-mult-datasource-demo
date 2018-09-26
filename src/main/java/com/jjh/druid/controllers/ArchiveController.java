package com.jjh.druid.controllers;

import com.jjh.druid.othermodels.Archive;
import com.jjh.druid.services.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArchiveController {
    @Autowired
    private ArchiveService archiveService;


    @RequestMapping("/a_save")
    public String save() {

        Archive archive = new Archive();
        archive.setINFO("ads");
        archiveService.save(archive);
        return "OK";
    }

    @RequestMapping("/a_find")
    public String find() {
        List<Archive> archiveList = archiveService.find("9bad06eb-4a22-4554-8a26-26a5711c4162");
        for (Archive archive : archiveList) {
            System.out.println(archive.getINFO());
        }
        return "OK";
    }

}
