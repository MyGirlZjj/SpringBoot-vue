package com.boylegu.springboot_vue.controller.pagination;

import com.boylegu.springboot_vue.dao.PersonsRepository;
import com.boylegu.springboot_vue.entities.Persons;
import com.boylegu.springboot_vue.entities.Ypxx;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/*
    Resolve due to @Autowired lead to NullPointerException problem

    Description：
    1. It's limited to general class to invoke spring bean Object.
    2. And This makes the sub package easy to scan by spring boot.

                                                      ———— @Boyle.gu
*/
@Component
class SpringUtilYpxx implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        if (SpringUtilYpxx.applicationContext == null) {

            SpringUtilYpxx.applicationContext = applicationContext;

        }
    }

    public static ApplicationContext getApplicationContext() {

        return applicationContext;

    }

    public static Object getBean(String name) {

        return getApplicationContext().getBean(name);

    }

    public static <T> T getBean(Class<T> clazz) {

        return getApplicationContext().getBean(clazz);

    }

    public static <T> T getBean(String name, Class<T> clazz) {

        return getApplicationContext().getBean(name, clazz);

    }
}


interface TypesYpxx {

    public Page<Ypxx> query();

    public Integer getCount();

    public Integer getPageNumber();

    public Long getTotal();

    public Object getContent();
}

class BasePaginationInfoYpxx {

    public Pageable pageable;

    public PersonsRepository instance = SpringUtil.getBean(PersonsRepository.class);

    public String bianma, pinming, pihao;

    public BasePaginationInfoYpxx(String bianmaName, String pinmingName, String pihao,Pageable pageable) {

        this.pageable = pageable;

        this.pihao = pihao;

        this.bianma = bianmaName;

        this.pinming = pinmingName;
    }
}

class AllTypeYpxx extends BasePaginationInfoYpxx implements TypesYpxx {

    public AllTypeYpxx(String bianma, String pinming,String pihao, Pageable pageable) {
        super(bianma, pinming, pihao,pageable);
    }
    public Page<Ypxx> query() {

        return this.instance.findAll(
                this.pageable
        );
    }
    public Integer getCount() {
        return this.query().getSize();
    }
    public Integer getPageNumber() {

        return this.query().getNumber();

    }
    public Long getTotal() {
        return this.query().getTotalElements();
    }
    public Object getContent() {
        return this.query().getContent();
    }
}

public class PaginationFormattingYpxx {

    private PaginationMultiTypeValuesHelper multiValue = new PaginationMultiTypeValuesHelper();

    private Map<String, PaginationMultiTypeValuesHelper> results = new HashMap<>();

    public Map<String, PaginationMultiTypeValuesHelper> filterQuery(String bianma, String pinming,String pihao, Pageable pageable) {

        TypesYpxx typeInstance;

        if (bianma.length() == 0 && pinming.length() == 0 ) {

            typeInstance = new AllTypeYpxx(bianma, pinming,pihao, pageable);

        } else if (bianma.length() > 0 && pinming.length() > 0) {

            typeInstance = new bianmapinmingType(bianma, pinming, pageable);

        } else {
            typeInstance = new bianmaType(bianma, pinming, pageable);
        }

        this.multiValue.setCount(typeInstance.getCount());

        this.multiValue.setPage(typeInstance.getPageNumber() + 1);

        this.multiValue.setResults(typeInstance.getContent());

        this.multiValue.setTotal(typeInstance.getTotal());

        this.results.put("data", this.multiValue);

        return results;
    }

}