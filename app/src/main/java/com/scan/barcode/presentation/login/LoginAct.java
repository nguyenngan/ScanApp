/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.presentation.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.scan.barcode.R;
import com.scan.barcode.data.common.StatusNetwork;
import com.scan.barcode.data.entities.User;
import com.scan.barcode.databinding.LoginActBinding;
import com.scan.barcode.presentation.ScanApplication;
import com.scan.barcode.presentation.common.AbsActivity;
import com.scan.barcode.presentation.main.MainAct;

import javax.inject.Inject;

/**
 * @author ndn
 * Created by ndn
 * Created on 2019-07-17.
 */
public class LoginAct extends AbsActivity {

    @Inject
    ViewModelProvider.Factory factory;

    LoginActBinding binding;
    LoginViewModel viewModel;

    private ProgressDialog progressDialog;

    public static Intent getIntent(Context context) {
        return new Intent(context, LoginAct.class);
    }

    @Override
    protected void initializeBindingViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.login_act);
        viewModel = ViewModelProviders.of(LoginAct.this, factory).get(LoginViewModel.class);
    }

    @Override
    protected void initializeLayout() {
        progressDialog = new ProgressDialog(LoginAct.this);
        progressDialog.setMessage("Loading...");
        initLoginResponse();
        initSignInHandler();
        initCacheUser();
    }

    private void initCacheUser() {
        viewModel.getCacheUser().observe(this, user -> {
            if (user == null) {
                return;
            }
            ScanApplication.getInstance().setUser(user);
            navigateMain();
        });
    }

    private void initLoginResponse() {
        viewModel.getUserResponse().observe(this, resource -> {
            if (resource != null) {
                if (resource.statusNetwork == StatusNetwork.LOADING) {
                    progressDialog.show();
                    return;
                }
                if (resource.statusNetwork == StatusNetwork.SUCCESS) {
                    if (resource.data != null) {
                        ScanApplication.getInstance().setUser(resource.data);
                        navigateMain();
                        progressDialog.dismiss();
                        return;
                    }
                }
                progressDialog.dismiss();
                Toast.makeText(this, "Login error", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Check sign in pattern and show error
     * Handle sign in when sign in bt click
     */
    private void initSignInHandler() {
        binding.loginBt.setOnClickListener(v -> doLogin());
        binding.passwordEdt.setImeOptions(EditorInfo.IME_ACTION_DONE);
        binding.passwordEdt.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE) {
                doLogin();
                return true;
            }
            return false;
        });
    }

    /**
     * Do sign in
     * Check username pattern
     * Check password pattern
     * Hide keyboard
     * UpdateChat sign in observer
     */
    private void doLogin() {
        if (binding.userNameEdt.getText() == null
                || TextUtils.isEmpty(binding.userNameEdt.getText().toString())) {
            Toast.makeText(this, "Username have require", Toast.LENGTH_LONG).show();
            return;
        }

        if (binding.passwordEdt.getText() == null || TextUtils.isEmpty(binding.passwordEdt.getText().toString())) {
            Toast.makeText(this, "Password have require", Toast.LENGTH_LONG).show();
            return;
        }

        hideSoftInput(this);
        viewModel.setUser(new User(binding.userNameEdt.getText().toString(),
                binding.passwordEdt.getText().toString()));
    }

    private void navigateMain() {
        startActivity(MainAct.getIntent(this));
        finish();
    }

    /**
     * Hide the soft input.
     *
     * @param activity The activity.
     */
    public static void hideSoftInput(final Activity activity) {
        InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        View view = activity.getCurrentFocus();
        if (view == null) view = new View(activity);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
