package com.genonbeta.TrebleShot.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.genonbeta.TrebleShot.R;
import com.genonbeta.TrebleShot.io.DocumentFile;

/**
 * Created by: veli
 * Date: 5/30/17 12:18 PM
 */

public class FolderCreationDialog extends AbstractSingleTextInputDialog
{
	public FolderCreationDialog(final Context context, final DocumentFile currentFolder, final OnFolderCreatedListener createdListener)
	{
		super(context);

		setTitle(R.string.text_createFolder);

		setOnProceedClickListener(R.string.butn_create, new OnProceedClickListener()
		{
			@Override
			public boolean onProceedClick(AlertDialog dialog)
			{
				String fileName = getEditText().getText().toString();
				DocumentFile createdFile = currentFolder.createDirectory(fileName);

				if (createdFile == null) {
					Toast.makeText(getContext(), R.string.mesg_folderCreateError, Toast.LENGTH_SHORT).show();
					return false;
				}

				createdListener.onFolderCreated(createdFile);
				dialog.dismiss();

				return true;
			}
		});
	}

	public interface OnFolderCreatedListener
	{
		void onFolderCreated(DocumentFile directoryFile);
	}
}