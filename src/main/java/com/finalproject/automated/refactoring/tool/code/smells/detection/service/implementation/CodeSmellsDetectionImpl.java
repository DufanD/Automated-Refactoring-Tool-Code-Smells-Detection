package com.finalproject.automated.refactoring.tool.code.smells.detection.service.implementation;

import com.finalproject.automated.refactoring.tool.code.smells.detection.service.CodeSmellsDetection;
import com.finalproject.automated.refactoring.tool.lazy.classes.detection.service.LazyClassDetection;
import com.finalproject.automated.refactoring.tool.longg.methods.detection.service.LongMethodsDetection;
import com.finalproject.automated.refactoring.tool.longg.parameter.methods.detection.service.LongParameterMethodsDetection;
import com.finalproject.automated.refactoring.tool.model.ClassModel;
import com.finalproject.automated.refactoring.tool.model.MethodModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author Dufan Quraish
 * @version 1.0.0
 * @since 19 August 2019
 */

@Service
public class CodeSmellsDetectionImpl implements CodeSmellsDetection {

    @Autowired
    private LongMethodsDetection longMethodsDetection;

    @Autowired
    private LongParameterMethodsDetection longParameterMethodsDetection;

    @Autowired
    private LazyClassDetection lazyClassDetection;

    @Value("${threshold.long.methods}")
    private Long longMethodsThreshold;

    @Value("${threshold.long.parameter.methods}")
    private Long longParameterMethodsThreshold;

    @Value("${threshold.lazy.class}")
    private Long lazyClassesThreshold;

    @Override
    public void detect(MethodModel methodModel) {
        detect(Collections.singletonList(methodModel));
    }

    @Override
    public void detectClass(ClassModel classModel) {
        detectClass(Collections.singletonList(classModel));
    }

    @Override
    public void detect(List<MethodModel> methodModels) {
        detectBloaters(methodModels);
    }

    @Override
    public void detectClass(List<ClassModel> classModels) {
        detectDispensables(classModels);
    }

    private void detectBloaters(List<MethodModel> methodModels) {
        longMethodsDetection.detect(methodModels, longMethodsThreshold);
        longParameterMethodsDetection.detect(methodModels, longParameterMethodsThreshold);
    }

    private void detectDispensables(List<ClassModel> classModels) {
        lazyClassDetection.detect(classModels, lazyClassesThreshold);
    }
}
