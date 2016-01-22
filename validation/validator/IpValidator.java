package com.qzj.logistics.validation.validator;

import android.content.Context;

import com.qzj.logistics.validation.ValidationExecutor;
import com.qzj.logistics.validation.ValidationModel;


/**
 * IP验证器
 */
public class IpValidator extends ValidationExecutor {

    private static final String IP_HINT = "还未填写";

    @Override
    public boolean doValidate(Context context, ValidationModel model) {
        EmptyValidator emptyValidator = new EmptyValidator();
        boolean isEmpty = emptyValidator.doValidate(context,model);
        if(isEmpty){
            if(isIpAddress(model.getEditText().getText().toString())){
                return true;
            }else{
                hintMsg = model.getModelName() + IP_HINT;
                if (model.getHintView() != null)
                    model.getHintView().setText(hintMsg);
            }
        }

        return false;
    }

}
