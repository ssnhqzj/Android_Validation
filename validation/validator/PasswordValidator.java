package com.qzj.logistics.validation.validator;

import android.content.Context;

import com.qzj.logistics.validation.ValidationExecutor;
import com.qzj.logistics.validation.ValidationModel;


/**
 * 密码一致性验证器
 */
public class PasswordValidator extends ValidationExecutor {

    private static final String PASSWORD_HINT = "两次输入的密码不一致";

    @Override
    public boolean doValidate(Context context, ValidationModel model) {
        EmptyValidator emptyValidator = new EmptyValidator();
        boolean isNotEmpty = emptyValidator.doValidateForRelate(context, model);
        if(isNotEmpty){
            String password = model.getEditText().getText().toString();
            String repeatPassword = model.getRelateEditText().getText().toString();
            if(password.equals(repeatPassword)){
                return true;
            }else{
                hintMsg = PASSWORD_HINT;
                if (model.getHintView() != null)
                    model.getHintView().setText(hintMsg);
            }
        }

        return false;
    }
}
