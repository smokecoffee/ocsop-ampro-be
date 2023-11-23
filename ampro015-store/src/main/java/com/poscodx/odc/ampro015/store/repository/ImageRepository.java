package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.ImageJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * Auto generated class
 * 
 * 자동생성 프로그램 버전 : 1.0.0
 * 생성일시 :  2023-08-26 21:42:35.32
 * @FileName : 클래스에 대한 한글 명칭
 * Change history
 * @수정날짜;SCR_NO;수정자;수정내용
 * @2023-08-26 21:42:35.32;00000;홍길동;최초생성
 * 
 */
public interface ImageRepository
    extends JpaRepository<ImageJpo, Integer>
{
    @Query(value = "SELECT *\n"+
            "FROM tb_a01_image i\n" +
            "WHERE 1=1\n" +
            "AND i.ASSET_ID = :assetId\n" +
            "AND i.DELETE_AT IS NULL", nativeQuery = true)
    Iterable<ImageJpo> findAllByAssetId(Integer assetId);
}
