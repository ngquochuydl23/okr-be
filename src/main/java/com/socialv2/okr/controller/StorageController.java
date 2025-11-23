package com.socialv2.okr.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class StorageController {

    @PostMapping("/storage/upload")
    @ResponseBody
    public ResponseEntity<String> uploadFile() {
        throw new NotImplementedException();
    }

    @GetMapping("/storage/{filename:.+}")
    @ResponseBody
    public ResponseEntity<String> getFileByName(@PathVariable String fileName) {
        throw new NotImplementedException();
    }
}
