package com.cloume.restapi.initializr.service;

import com.cloume.commons.beanutils.IConverter;
import com.cloume.commons.beanutils.Updater;
import com.cloume.restapi.initializr.model.BaseResource;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IBaseService <T>{

    /**
     * 创建新的T
     * @return
     */
    T create();

    /**
     * 返回与Service对应的Repository
     * @return
     */
    MongoRepository<T, String> getRepository();

    /**
     * 更新
     * @param t
     * @return
     */
    default T wrap(T t, Map<String, Object> map){
        return wrap(t, map, null);
    }

    /**
     * 更新
     * @param t
     * @param map
     * @param converter
     * @return
     */
    default T wrap(T t, Map<String, Object> map, IConverter converter){
        if(map == null){ return null; }
        return Updater.wrap(t).update(map, converter);
    }

    /**
     * 根据主键ID进行查找
     * @param id
     * @return
     */
    default T find(String id){
        Optional optional = getRepository().findById(id);
        if (optional != null && optional.isPresent()) {
            return (T) optional.get();
        }
        return null;
    }

    /**
     * 根据多个ID进行获取
     * @param ids
     * @return
     */
    default List<T> findByIDs(List<String> ids){
        return (List<T>) getRepository().findAllById(ids);
    }

    /**
     * 保存
     * @param map
     * @return
     */
    default T save(Map<String, Object> map){
        T t = this.wrap(this.create(), map);
        if(t == null) {
            return null;
        }
        return getRepository().save(t);
    }

    /**
     * 保存
     * @param t
     */
    default T save(T t){
        if(t == null) {
            return null;
        }
        return getRepository().save(t);
    }

    /**
     * 保存, 新创建的时候使用, 如果需要更新则不推荐此方法, 效率极低
     * 如果是更新, 请使用
     * @param tList
     */
    default List<T> save(List<T> tList){
        if(tList == null || tList.isEmpty()){
            return null;
        }
        return getRepository().saveAll(tList);
    }

    /**
     * 更新
     * @param id
     * @param map
     * @return
     */
    default T update(String id, Map<String, Object> map){
        T t = getRepository().findById(id).get();
        return update(t, map);
    }

    /**
     * 更新
     * @param t
     * @param map
     * @return
     */
    default T update(T t, Map<String, Object> map){
        if(t == null) {
            return t;
        }
        return getRepository().save(this.wrap(t, map));
    }

    /**
     * 删除
     * @param id
     */
    default T delete(String id) {

        return delete(getRepository().findById(id).get());
    }

    /**
     * 删除
     * @param t
     */
    default T delete(T t) {
        if(t == null) {
            return null;
        }
        if(t instanceof BaseResource){
            ((BaseResource) t).setIsRemoved(true);
            return getRepository().save(t);
        }else{
            getRepository().delete(t);
            return t;
        }
    }

    /**
     * 根据id列表批量删除
     * @param ids
     */
    default void batchDelete(List<String> ids){
        if(ids == null) {
            return;
        }

        ids.stream().forEach(t -> {
            delete(t);
        });
    }

    /**
     * 根据实体列表批量删除
     * @param ts
     */
    default void batchDeleteEntity(List<T> ts){
        if(ts == null) {
            return;
        }

        ts.stream().forEach(t -> {
            delete(t);
        });
    }

    /**
     * 默认按照 id 降序排列
     * @return
     */
    default Sort defaultSort(){
        return Sort.by(Sort.Direction.DESC, "_id");
    }
}