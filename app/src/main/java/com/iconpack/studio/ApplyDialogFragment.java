package com.iconpack.studio;

import androidx.appcompat.app.AlertDialog;
import android.app.Dialog;
import androidx.fragment.app.DialogFragment;
import androidx.annotation.NonNull;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class ApplyDialogFragment extends DialogFragment {
    
    private List<LauncherItem> launcherList;
    
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        
        View dialogView = requireActivity().getLayoutInflater().inflate(R.layout.dialog_apply, null);
        ListView launcherListView = dialogView.findViewById(R.id.launcher_list);
        
        setupLauncherList();
        
        ArrayAdapter<LauncherItem> adapter = new ArrayAdapter<LauncherItem>(
            requireActivity(), 
            android.R.layout.simple_list_item_1, 
            launcherList
        );
        
        launcherListView.setAdapter(adapter);
        launcherListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LauncherItem selectedLauncher = launcherList.get(position);
                applyIconPack(selectedLauncher);
                dismiss();
            }
        });
        
        builder.setView(dialogView)
               .setTitle("Select Launcher")
               .setNegativeButton("Cancel", null);
        
        return builder.create();
    }
    
    private void setupLauncherList() {
        launcherList = new ArrayList<>();
        PackageManager pm = requireActivity().getPackageManager();
        
        // Nova Launcher
        if (isPackageInstalled("com.teslacoilsw.launcher", pm)) {
            launcherList.add(new LauncherItem("Nova Launcher", "com.teslacoilsw.launcher", "com.teslacoilsw.launcher.NovaLauncher"));
        }
        
        // Action Launcher
        if (isPackageInstalled("com.actionlauncher.playstore", pm)) {
            launcherList.add(new LauncherItem("Action Launcher", "com.actionlauncher.playstore", "com.actionlauncher.ActionLauncher"));
        }
        
        // Apex Launcher
        if (isPackageInstalled("com.anddoes.launcher", pm)) {
            launcherList.add(new LauncherItem("Apex Launcher", "com.anddoes.launcher", "com.anddoes.launcher.Launcher"));
        }
        
        // Smart Launcher
        if (isPackageInstalled("ginlemon.flowerfree", pm)) {
            launcherList.add(new LauncherItem("Smart Launcher", "ginlemon.flowerfree", "ginlemon.flowerfree.SmartLauncher"));
        }
        
        // Add manual apply option
        launcherList.add(new LauncherItem("Apply Manually", "", ""));
        
        if (launcherList.isEmpty()) {
            launcherList.add(new LauncherItem("No compatible launchers found", "", ""));
        }
    }
    
    private boolean isPackageInstalled(String packageName, PackageManager pm) {
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
    
    private void applyIconPack(LauncherItem launcher) {
        if (launcher.getPackageName().isEmpty()) {
            // Manual apply or no launcher
            showManualInstructions();
            return;
        }
        
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(launcher.getPackageName(), launcher.getClassName()));
            intent.putExtra("com.teslacoilsw.launcher.extra.ICON_THEME_TYPE", "GO_LAUNCHER_EX");
            intent.putExtra("com.teslacoilsw.launcher.extra.ICON_THEME_PACKAGE", requireActivity().getPackageName());
            
            startActivity(intent);
            
            Toast.makeText(requireActivity(), "Opening " + launcher.getName() + " to apply icon pack", 
                         Toast.LENGTH_SHORT).show();
                         
        } catch (Exception e) {
            showManualInstructions();
        }
    }
    
    private void showManualInstructions() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Manual Application")
               .setMessage("To apply this icon pack manually:\n\n" +
                          "1. Open your launcher settings\n" +
                          "2. Look for 'Icon Pack' or 'Themes' option\n" +
                          "3. Select 'IconPack Studio' from the list\n" +
                          "4. Apply the icon pack\n\n" +
                          "Note: Steps may vary depending on your launcher.")
               .setPositiveButton("OK", null)
               .show();
    }
    
    private static class LauncherItem {
        private String name;
        private String packageName;
        private String className;
        
        public LauncherItem(String name, String packageName, String className) {
            this.name = name;
            this.packageName = packageName;
            this.className = className;
        }
        
        public String getName() { return name; }
        public String getPackageName() { return packageName; }
        public String getClassName() { return className; }
        
        @Override
        public String toString() {
            return name;
        }
    }
}

