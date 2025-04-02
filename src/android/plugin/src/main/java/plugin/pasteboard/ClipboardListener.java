//
//  clipboardListener.java
//  pasteboard Plugin
//
//  Copyright (c) 2013 Coronalabs. All rights reserved.
//

// Package name
package plugin.pasteboard;

import android.content.Context;

import com.ansca.corona.CoronaActivity;
import com.ansca.corona.CoronaEnvironment;


// Clipboard listener class
public class ClipboardListener {
    private android.content.ClipboardManager clipboardManager;
    private android.content.ClipboardManager.OnPrimaryClipChangedListener primaryClipChangedListener;

    private void setNewPasteboardItem(Context context) {

        if (clipboardManager == null) {
            clipboardManager = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        }

        // If the Clipboard contains data, save it to shared
        if (clipboardManager.hasPrimaryClip()) {
            android.content.ClipData clipData = clipboardManager.getPrimaryClip();
            // Check if clipData is not null
            if (clipData != null) {
                android.content.ClipData.Item item = clipData.getItemAt(0);
                shared.setCurrentPasteboardItem(shared.ApiLevel11.coerceToString(context, item));
            }
        }
    }


    // Function to add the clipboard listener
    public void addClipChangedListener() {
        // Verify environment
        final Context context = CoronaEnvironment.getApplicationContext();
        if (context == null) return;

        // Verify environment
        CoronaActivity coronaActivity = CoronaEnvironment.getCoronaActivity();
        if (coronaActivity == null) return;

        coronaActivity.runOnUiThread(new Runnable() {
            public void run() {

                if (clipboardManager == null) {
                    clipboardManager = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                }
                if (clipboardManager == null) return;


                if (primaryClipChangedListener == null) {
                    primaryClipChangedListener = new android.content.ClipboardManager.OnPrimaryClipChangedListener() {
                        public void onPrimaryClipChanged() {
                            setNewPasteboardItem(context);
                        }
                    };
                }

                // Grab the initial clipboard contents and put them in pasteboard, if any.
                setNewPasteboardItem(context);
                clipboardManager.addPrimaryClipChangedListener(primaryClipChangedListener);
            }
        });
    }

    // Function to remove the clipboard listener
    public void removeClipChangedListener() {
        // Verify environment
        Context context = CoronaEnvironment.getApplicationContext();
        if (context == null) return;

        // Remove the clip listener
        if (clipboardManager == null || primaryClipChangedListener == null) {
            return;
        }
        try {
            clipboardManager.removePrimaryClipChangedListener(primaryClipChangedListener);
        } catch (Throwable ignore) {
        }
    }
}
