package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;


/**
 * @author ShiRongbao
 * @create 2024/2/29 22:03
 * @description: 自定义切面，实现公共字段自动填充处理逻辑
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {


    /**
     * 切入点
     * 切点表达式：匹配所有在 com.sky.mapper 包及其子包下定义的任何方法的执行，参数任意。 并且有标注AutoFill注解
     */
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut() {
    }


    /**
     * 这里使用前置通知，要在执行mapper方法之前就为公共字段赋值
     */
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("开始进行公共字段的自动填充");

        // 获取到当前被拦截的方法上的数据库操作类型 UPDATE,INSERT
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFill annotation = signature.getMethod().getAnnotation(AutoFill.class);  // 获取到当前方法上的AutoFill注解
        OperationType operationType = annotation.value();  // 获得到数据库操作类型
        // 获取到当前被拦截的方法的参数  也就是实体对象
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return;
        }
        Object entity = args[0];
        // 准备赋值的数据LocalDateTime, empId
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();
        // 根据当前不同的操作类型，为对应的属性通过反射来赋值
        if (operationType == OperationType.INSERT) {
            // 如果是插入操作，那么四个字段都要赋值
            try {
                Class<?> aClass = entity.getClass();  // 拿到实体类
                // 通过实体类获取到set方法
                Method setCreateTime = aClass.getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUser = aClass.getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateTime = aClass.getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = aClass.getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                // 执行set方法，将实体对象与参数传入
                setCreateTime.invoke(entity, now);
                setCreateUser.invoke(entity, currentId);
                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (operationType == OperationType.UPDATE) {
            // 如果是更新操作，那么只需要给两个字段赋值
            try {
                Class<?> aClass = entity.getClass();  // 拿到实体类
                // 通过实体类获取到set方法
                Method setUpdateTime = aClass.getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = aClass.getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                // 执行set方法，将实体对象与参数传入
                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
