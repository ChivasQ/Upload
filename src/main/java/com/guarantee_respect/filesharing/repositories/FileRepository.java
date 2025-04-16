package com.guarantee_respect.filesharing.repositories;

import com.guarantee_respect.filesharing.models.FileEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface FileRepository extends CrudRepository<FileEntity, UUID> {
}
