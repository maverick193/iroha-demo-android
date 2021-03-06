/*
Copyright Soramitsu Co., Ltd. 2016 All Rights Reserved.
http://soramitsu.co.jp

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package io.soramitsu.examplepoint.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.soramitsu.examplepoint.R;
import io.soramitsu.examplepoint.databinding.DialogBinding;
import io.soramitsu.examplepoint.util.AndroidSupportUtil;

public class ErrorDialog {
    private AlertDialog dialog;
    private DialogBinding dialogErrorBinding;

    public ErrorDialog(LayoutInflater inflater) {
        dialogErrorBinding = DialogBinding.inflate(inflater);
    }

    public void show(Activity activity, String message, final Dialog.OnClickListener onClickListener) {
        if (dialog != null && dialog.isShowing()) {
            return;
        }

        dialogErrorBinding.title.setText(activity.getApplicationContext().getString(R.string.error));
        dialogErrorBinding.title.setTextColor(
                AndroidSupportUtil.getColor(activity.getApplicationContext(), R.color.colorAccent)
        );
        dialogErrorBinding.message.setText(message);
        dialogErrorBinding.ok.setTextColor(
                AndroidSupportUtil.getColor(activity.getApplicationContext(), R.color.red600)
        );
        dialogErrorBinding.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
            }
        });

        ViewGroup parent = ((ViewGroup) dialogErrorBinding.root.getParent());
        if (parent != null) {
            parent.removeView(dialogErrorBinding.root);
        }

        dialog = new AlertDialog.Builder(activity)
                .setView(dialogErrorBinding.root)
                .create();
        dialog.show();
    }
}
