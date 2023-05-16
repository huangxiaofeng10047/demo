package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.time.LocalDate;

/**
 * @author hxf
 * @date 2023年05月15日 17:07
 */
@Document(indexName = "user",createIndex = true)
@Setting(shards = 3, replicas = 1,refreshInterval="1ms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    private Long id;

    @Field(type = FieldType.Keyword)
    private String username;

    @Field(type = FieldType.Integer)
    private Integer age;

    @Field(type = FieldType.Date,format={DateFormat.basic_date, DateFormat.year_month_day})
    private LocalDate birthday;

    @Field(type = FieldType.Keyword)
    private String province;

    @Field(type = FieldType.Keyword)
    private String city;

    @Field(type = FieldType.Keyword)
    private String district;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
//   @Field(type = FieldType.Text)
    private String address;

    @GeoPointField
    private GeoPoint location;

    @Field(index = false, type = FieldType.Keyword)
    private String photo;

    @Field(type = FieldType.Text, analyzer = "ik_smart")
//    @Field(type = FieldType.Text)
    private String about;
}
