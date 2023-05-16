package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.stream.Stream;
/**
 * @author hxf
 * @date 2023年05月15日 17:08
 */
public interface UserRepository extends ElasticsearchRepository<User, Long> {

    //统计城市某个区域总人数
    long countByDistrict(String district);
    //按城市区域搜索
    List<User> findByDistrictOrderByIdDesc(String district);
    //按地址搜索
    List<User> findByAddress(String address);
    //按区域或地址搜索，两个条件满足一个即可
    List<User> findByDistrictOrAddress(String district,String address);
    //按区域和地址搜索，两个条件都必须满足
    List<User> findByDistrictAndAddress(String district,String address);
    //按年龄区间搜索
    List<User> findByAgeBetween(int min,int max);
    //按地址分页搜索
    Page<User> findByAddress(String address, Pageable pageable);
    //按简介搜索
    @Query(""" 
        {"match": {"about": {"query": "?0"}}}
        """)
    Stream<User> findByAbout(String about);
    //多条件组合搜索
    @Query("""
            {"bool":{"must":[{"match":{"city":{"query": "?0"}}},{"match":{"sex":{"query": "?1"}}},{"range":{"age":{"gte":?2,"lte":?3}}}]}}
            """)
    Page<User> search(String city, String sex, Integer minAge, Integer maxAge, Pageable pageable);
}
