package com.xuecheng.xcservicecms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.CustomException;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResultCode;
import com.xuecheng.xcservicecms.dao.CmsPageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by tangjiejin on 2019/03/06
 */
@Service
public class CmsPageService {

    @Autowired
    CmsPageRepository cmsPageRepository;

    public QueryResponseResult getReturn(Page page){

        QueryResult<CmsPage> cmsPageQueryResult = new QueryResult<CmsPage>();
        cmsPageQueryResult.setList(page.getContent());
        cmsPageQueryResult.setTotal(page.getTotalElements());

        return new QueryResponseResult(CommonCode.SUCCESS,cmsPageQueryResult);
    }

    public QueryResponseResult findList(Integer pageIndex, Integer pageSize, QueryPageRequest quseryPageResult) {
        if (quseryPageResult == null){
            quseryPageResult = new QueryPageRequest();
        }

        CmsPage cmsPage = new CmsPage();

        //条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        exampleMatcher = exampleMatcher.withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains())
                        .withMatcher("pageName",ExampleMatcher.GenericPropertyMatchers.endsWith())
                        .withMatcher("pageId",ExampleMatcher.GenericPropertyMatchers.startsWith());

        //页面别名模糊查询，需要自定义字符串的匹配器实现模糊查询
        // ExampleMatcher.GenericPropertyMatchers.contains() 包含
        //ExampleMatcher.GenericPropertyMatchers.startsWith()//开头匹配

        if (StringUtils.isNotEmpty(quseryPageResult.getPageAliase())){
            cmsPage.setPageAliase(quseryPageResult.getPageAliase());
        }
        if (StringUtils.isNotEmpty(quseryPageResult.getPageName())){
            cmsPage.setPageName(quseryPageResult.getPageName());
        }
        if (StringUtils.isNotEmpty(quseryPageResult.getPageId())){
            cmsPage.setPageId(quseryPageResult.getPageId());
        }

        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);

        if (pageIndex <= 0){
            pageIndex = 0;
        }else {
            pageIndex = pageIndex -1;
        }

        if (pageSize <= 0){
            pageSize = 10;
        }
        //分页对象
        PageRequest pageRequest = new PageRequest(pageIndex, pageSize);
        //分页查询
        Page<CmsPage> page = cmsPageRepository.findAll(example,pageRequest);

        return getReturn(page);

    }

    public CmsPageResult addPage(CmsPage cmsPage) {
        // 判断该页面是否存在
        CmsPage isCmsPage = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(
                cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());

        if (isCmsPage != null){
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
        }

        // 保存页面
        CmsPage saveCmsPage = cmsPageRepository.save(cmsPage);

        return new CmsPageResult(CommonCode.SUCCESS,saveCmsPage);
    }

    public CmsPageResult edit(CmsPage cmsPage) {
        // 根据pageId查询该页面是否存在
        CmsPage findPage = findPageById(cmsPage.getPageId());
        if (findPage == null){
            ExceptionCast.cast(CmsCode.CMS_PAGEID_NOTEXITS);
        }
        //更新所属站点
        findPage.setTemplateId(cmsPage.getTemplateId());
        findPage.setSiteId(cmsPage.getSiteId());
        //更新页面别名
        findPage.setPageAliase(cmsPage.getPageAliase());
        //更新页面名称
        findPage.setPageName(cmsPage.getPageName());
        //更新访问路径
        findPage.setPageWebPath(cmsPage.getPageWebPath());
        //更新物理路径
        findPage.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
        //执行更新
        CmsPage save = cmsPageRepository.save(findPage);
        if (save == null) {
            ExceptionCast.cast(CmsCode.CMS_PAGEEDIT_FAILD);
        }
       //返回成功
        CmsPageResult cmsPageResult = new CmsPageResult(CommonCode.SUCCESS, save);
        return cmsPageResult;
    }

    public CmsPage findPageById(String pageId){
        Optional<CmsPage> optional = cmsPageRepository.findById(pageId);
        if (optional.isPresent()){
            CmsPage cmsPage = optional.get();
            return cmsPage;
        }
        return null;
    }

    public void delete(String pageId) {
        CmsPage findPage = findPageById(pageId);
        if (findPage == null){
            ExceptionCast.cast(CmsCode.CMS_PAGEID_NOTEXITS);
        }
        cmsPageRepository.deleteById(pageId);
    }
}





















