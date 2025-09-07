package com.example.buysellProject.repositories;

import com.example.buysellProject.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
