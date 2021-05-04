package com.ProyectoASO.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.ProyectoASO.exceptions.FileSystemException;

public interface IFileStorageService{
	
	public void init( )throws FileSystemException;
	
	public void init(String dirPath) throws FileSystemException;
	
	public Resource loadFile(String name,String dirPath) throws FileSystemException;
	
	public void delete(String name,String dirPath) throws FileSystemException;
	
	public void saveFile(MultipartFile file,String dirPath) throws FileSystemException;

}
