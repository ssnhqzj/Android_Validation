package com.qzj.logistics.validation.validator;

import android.content.Context;
import android.text.TextUtils;

import com.qzj.logistics.validation.ValidationExecutor;
import com.qzj.logistics.validation.ValidationModel;


/**
 * 空验证器
 */
public class EmptyValidator extends ValidationExecutor {

    private static final String EMPTY_HINT = "还未填写";

    @Override
    public boolean doValidate(Context context, ValidationModel model) {
        if(model.getEditText() == null || TextUtils.isEmpty(model.getEditText().getText())){
            hintMsg = model.getModelName() + EMPTY_HINT;
            if (model.getHintView() != null && hintMsg != null) {
                model.getHintView().setText(hintMsg);
            }

            return false;
        }

        return true;
    }

    /**
     * 关联空验证--验证model中的relateEditText和editText
     * @param context
     * @param model
     * @return
     */
    public boolean doValidateForRelate(Context context, ValidationModel model) {
        boolean isNotEmpty = doValidate(context,model);
        if (!isNotEmpty) return false;
        boolean isNotEmptyRelate = true;
        if(model.getRelateEditText() == null || TextUtils.isEmpty(model.getRelateEditText().getText())){
            if (model.getHintView() != null)
                model.getHintView().setText(model.getRelateModelName() + EMPTY_HINT);
            isNotEmptyRelate = false;
        }

        if(isNotEmpty && isNotEmptyRelate){
            return true;
        }

        return false;
    }
}
