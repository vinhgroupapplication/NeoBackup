package com.newsaigonsoft.neoegov.MyInterface;

import android.content.Context;

import java.util.List;

import com.newsaigonsoft.neoegov.Subjects.AttachFile;

/**
 * Created by Vinh on 10/2/2017.
 */

public interface OpenFileItemClick {
   public abstract void checkRunTimePermission(int position, List<AttachFile> arrFileAttach, Context context);
}
