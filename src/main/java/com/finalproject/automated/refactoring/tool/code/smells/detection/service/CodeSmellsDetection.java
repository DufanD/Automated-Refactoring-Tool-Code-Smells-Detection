package com.finalproject.automated.refactoring.tool.code.smells.detection.service;

import com.finalproject.automated.refactoring.tool.model.ClassModel;
import com.finalproject.automated.refactoring.tool.model.MethodModel;
import lombok.NonNull;

import java.util.List;

/**
 * @author Dufan Quraish
 * @version 1.0.0
 * @since 19 August 2019
 */

public interface CodeSmellsDetection {

    void detect(@NonNull MethodModel methodModel);

    void detectClass(@NonNull ClassModel classModel);

    void detect(@NonNull List<MethodModel> methodModels);

    void detectClass(@NonNull List<ClassModel> classModels);
}
