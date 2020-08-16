package com.lrh.auth.dao;

import com.lrh.auth.model.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/15
 */
public interface FileUploadRepository extends JpaRepository<FileUpload, Integer> {

}
