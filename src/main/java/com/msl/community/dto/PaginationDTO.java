package com.msl.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 *  分页
 * */
@Data
public class PaginationDTO {
    private List<QuesetionDTO> questions ; //问题集合
    private boolean showPrevious;//向前按钮
    private boolean showFirstPage; //第一页
    private boolean showNext; //下一页按钮
    private boolean showEndPage; //最后一页按钮
    private Integer page; //当前页面
    private List<Integer> pages = new ArrayList<>(); //展示的页码数组
    private Integer totalPage; //总共的页数

    //设置查询当前页需要展示的内容
    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage =totalPage;
        this.page = page;

        pages.add(page);
        for(int i = 1; i <= 3; i++){
            //第一页时，只展示3个，之后每一页都数组的个数都小于7
            if(page-i > 0){
                pages.add(0,page-i);
            }
            if(page + i <= totalPage){
                pages.add(page + i);
            }
        }
        //是否展示上一页按钮
        if(page == 1){
            showPrevious = false;
        }else {
            showPrevious =true;
        }
        //是否展示下一页按钮
        if(page == totalPage){
            showNext = false;
        }else {
            showNext = true;
        }
        //判断是否展示首页按钮
        if(pages.contains(1)){
            showFirstPage = false;
        }else {
            showFirstPage = true;
        }

        //判断是否展示最后一页按钮
        if(pages.contains(totalPage)){
            showEndPage = false;
        }else {
            showEndPage = true;
        }
    }
}
