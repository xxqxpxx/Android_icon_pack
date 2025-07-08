package com.iconpack.studio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    private GridView iconGrid;
    private TextView appTitle;
    private Button applyButton;
    private IconAdapter iconAdapter;
    private List<IconItem> iconList;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initializeViews();
        setupIconGrid();
        checkLauncherCompatibility();
    }
    
    private void initializeViews() {
        iconGrid = findViewById(R.id.icon_grid);
        appTitle = findViewById(R.id.app_title);
        applyButton = findViewById(R.id.apply_button);
        
        if (appTitle != null) {
            appTitle.setText(getString(R.string.app_name));
        }
        
        if (applyButton != null) {
            // Make sure button is enabled and clickable
            applyButton.setEnabled(true);
            applyButton.setClickable(true);
            applyButton.setFocusable(true);
            
            // Set up the click listener
            applyButton.setOnClickListener(v -> {
                try {
                    showApplyDialog();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
    
    private void setupIconGrid() {
        iconList = new ArrayList<>();
        loadIconList();
        
        iconAdapter = new IconAdapter(this, iconList);
        iconGrid.setAdapter(iconAdapter);
        iconGrid.setNumColumns(4);
    }
    
    private void loadIconList() {
        // System Apps
        iconList.add(new IconItem("Settings", R.drawable.settings, "com.android.settings"));
        iconList.add(new IconItem("Camera", R.drawable.camera, "com.android.camera2"));
        iconList.add(new IconItem("Gallery", R.drawable.gallery, "com.android.gallery3d"));
        iconList.add(new IconItem("Calculator", R.drawable.calculator, "com.android.calculator2"));
        iconList.add(new IconItem("Clock", R.drawable.clock, "com.android.deskclock"));
        iconList.add(new IconItem("Contacts", R.drawable.contacts, "com.android.contacts"));
        iconList.add(new IconItem("Phone", R.drawable.phone, "com.android.dialer"));
        iconList.add(new IconItem("Messages", R.drawable.messages, "com.android.messaging"));
        iconList.add(new IconItem("Files", R.drawable.files, "com.android.documentsui"));
        iconList.add(new IconItem("Music", R.drawable.music, "com.android.music"));
        iconList.add(new IconItem("Downloads", R.drawable.downloads, "com.android.providers.downloads.ui"));
        
        // Google Apps
        iconList.add(new IconItem("Chrome", R.drawable.google_chrome, "com.android.chrome"));
        iconList.add(new IconItem("Gmail", R.drawable.gmail, "com.google.android.gm"));
        iconList.add(new IconItem("Maps", R.drawable.maps, "com.google.android.apps.maps"));
        iconList.add(new IconItem("YouTube", R.drawable.youtube, "com.google.android.youtube"));
        iconList.add(new IconItem("Play Store", R.drawable.playstore, "com.android.vending"));
        iconList.add(new IconItem("Google Drive", R.drawable.google_drive, "com.google.android.apps.docs"));
        iconList.add(new IconItem("Google Docs", R.drawable.google_docs, "com.google.android.apps.docs.editors.docs"));
        iconList.add(new IconItem("Google Sheets", R.drawable.google_sheets, "com.google.android.apps.docs.editors.sheets"));
        
        // Messaging Apps
        iconList.add(new IconItem("WhatsApp", R.drawable.whatsapp, "com.whatsapp"));
        iconList.add(new IconItem("Telegram", R.drawable.telegram, "org.telegram.messenger"));
        iconList.add(new IconItem("Viber", R.drawable.viber, "com.viber.voip"));
        iconList.add(new IconItem("Skype", R.drawable.skype, "com.skype.raider"));
        iconList.add(new IconItem("Discord", R.drawable.discord, "com.discord"));
        
        // Social Media Apps
        iconList.add(new IconItem("Instagram", R.drawable.instagram, "com.instagram.android"));
        iconList.add(new IconItem("Facebook", R.drawable.facebook, "com.facebook.katana"));
        iconList.add(new IconItem("Twitter", R.drawable.twitter, "com.twitter.android"));
        iconList.add(new IconItem("Snapchat", R.drawable.snapchat, "com.snapchat.android"));
        iconList.add(new IconItem("TikTok", R.drawable.tiktok, "com.zhiliaoapp.musically"));
        iconList.add(new IconItem("LinkedIn", R.drawable.linkedin, "com.linkedin.android"));
        iconList.add(new IconItem("Reddit", R.drawable.reddit, "com.reddit.frontpage"));
        
        // Entertainment Apps
        iconList.add(new IconItem("Netflix", R.drawable.netflix, "com.netflix.mediaclient"));
        iconList.add(new IconItem("Amazon Prime", R.drawable.amazon_prime, "com.amazon.avod.thirdpartyclient"));
        iconList.add(new IconItem("Hulu", R.drawable.hulu, "com.hulu.plus"));
        iconList.add(new IconItem("Disney+", R.drawable.disney_plus, "com.disney.disneyplus"));
        iconList.add(new IconItem("Spotify", R.drawable.spotify, "com.spotify.music"));
        iconList.add(new IconItem("Apple Music", R.drawable.apple_music, "com.apple.android.music"));
        iconList.add(new IconItem("YouTube Music", R.drawable.youtube_music, "com.google.android.apps.youtube.music"));
        
        // Photography Apps
        iconList.add(new IconItem("VSCO", R.drawable.vsco, "com.vsco.cam"));
        iconList.add(new IconItem("Lightroom", R.drawable.lightroom, "com.adobe.lrmobile"));
        
        // Productivity & Business Apps
        iconList.add(new IconItem("Zoom", R.drawable.zoom, "us.zoom.videomeetings"));
        iconList.add(new IconItem("Microsoft Teams", R.drawable.teams, "com.microsoft.teams"));
        iconList.add(new IconItem("Microsoft Office", R.drawable.office, "com.microsoft.office.officehubrow"));
        
        // Cloud Storage Apps
        iconList.add(new IconItem("Dropbox", R.drawable.dropbox, "com.dropbox.android"));
        iconList.add(new IconItem("OneDrive", R.drawable.onedrive, "com.microsoft.skydrive"));
        
        // Finance Apps
        iconList.add(new IconItem("PayPal", R.drawable.paypal, "com.paypal.android.p2pmobile"));
        iconList.add(new IconItem("Cash App", R.drawable.cash_app, "com.squareup.cash"));
        iconList.add(new IconItem("Venmo", R.drawable.venmo, "com.venmo"));
        iconList.add(new IconItem("Coinbase", R.drawable.coinbase, "com.coinbase.android"));
        iconList.add(new IconItem("Robinhood", R.drawable.robinhood, "com.robinhood.android"));
        
        // Dating & Lifestyle Apps  
        iconList.add(new IconItem("Tinder", R.drawable.tinder, "com.tinder"));
        
        // Food & Delivery Apps
        iconList.add(new IconItem("Uber Eats", R.drawable.uber_eats, "com.ubercab.eats"));
        iconList.add(new IconItem("DoorDash", R.drawable.doordash, "com.dd.doordash"));
        iconList.add(new IconItem("Grubhub", R.drawable.grubhub, "com.grubhub.android"));
        iconList.add(new IconItem("Starbucks", R.drawable.starbucks, "com.starbucks.mobilecard"));
        
        // Gaming Apps
        iconList.add(new IconItem("Steam", R.drawable.steam, "com.valvesoftware.android.steam.community"));
        iconList.add(new IconItem("Twitch", R.drawable.twitch, "tv.twitch.android.app"));
        iconList.add(new IconItem("PlayStation", R.drawable.playstation, "com.playstation.psapp"));
        iconList.add(new IconItem("Xbox", R.drawable.xbox, "com.microsoft.xboxone.smartglass"));
        
        // Developer Tools
        iconList.add(new IconItem("GitHub", R.drawable.github, "com.github.android"));
        iconList.add(new IconItem("Slack", R.drawable.slack, "com.Slack"));
        iconList.add(new IconItem("Notion", R.drawable.notion, "notion.id"));
        iconList.add(new IconItem("Trello", R.drawable.trello, "com.trello"));
        
        // Design & Creative Apps
        iconList.add(new IconItem("Pinterest", R.drawable.pinterest, "com.pinterest"));
        iconList.add(new IconItem("Behance", R.drawable.behance, "com.adobe.creativeapps.behance"));
        iconList.add(new IconItem("Dribbble", R.drawable.dribbble, "com.dribbble.android"));
        
        // Travel & Booking Apps
        iconList.add(new IconItem("Airbnb", R.drawable.airbnb, "com.airbnb.android"));
        iconList.add(new IconItem("Booking.com", R.drawable.booking, "com.booking"));
        
        // Shopping Apps
        iconList.add(new IconItem("eBay", R.drawable.ebay, "com.ebay.mobile"));
        iconList.add(new IconItem("Etsy", R.drawable.etsy, "com.etsy.android"));
        
        // Finance & Banking Apps
        iconList.add(new IconItem("Banking", R.drawable.banking, "com.bankofamerica.digitalwallet"));
        
        // Security Apps
        iconList.add(new IconItem("VPN", R.drawable.vpn, "com.nordvpn.android"));
        
        // Transportation Apps
        iconList.add(new IconItem("Uber", R.drawable.uber, "com.ubercab"));
        iconList.add(new IconItem("Uber Driver", R.drawable.uber_driver, "com.ubercab.driver"));
        iconList.add(new IconItem("Lyft", R.drawable.lyft, "me.lyft.android"));
        
        // Education Apps
        iconList.add(new IconItem("Duolingo", R.drawable.duolingo, "com.duolingo"));
        iconList.add(new IconItem("Khan Academy", R.drawable.khan_academy, "org.khanacademy.android"));
        iconList.add(new IconItem("Coursera", R.drawable.coursera, "org.coursera.android"));
        
        // Fitness & Health Apps
        iconList.add(new IconItem("Fitness", R.drawable.fitness, "com.google.android.apps.fitness"));
        iconList.add(new IconItem("Nike Run Club", R.drawable.nike_run_club, "com.nike.plusone"));
        iconList.add(new IconItem("MyFitnessPal", R.drawable.myfitnesspal, "com.myfitnesspal.android"));
        iconList.add(new IconItem("Headspace", R.drawable.headspace, "com.getsomeheadspace.android"));
        
        // News & Media Apps
        iconList.add(new IconItem("News", R.drawable.news, "com.google.android.apps.magazines"));
        iconList.add(new IconItem("CNN", R.drawable.cnn, "com.cnn.mobile.android.phone"));
        iconList.add(new IconItem("BBC News", R.drawable.bbc_news, "bbc.mobile.news.ww"));
        iconList.add(new IconItem("Flipboard", R.drawable.flipboard, "flipboard.app"));
        iconList.add(new IconItem("Pocket", R.drawable.pocket_app, "com.ideashower.readitlater.pro"));
        
        // Additional Messaging Apps
        iconList.add(new IconItem("Signal", R.drawable.signal, "org.thoughtcrime.securesms"));
        iconList.add(new IconItem("LINE", R.drawable.line, "jp.naver.line.android"));
        iconList.add(new IconItem("WeChat", R.drawable.wechat, "com.tencent.mm"));
        iconList.add(new IconItem("Kik", R.drawable.kik, "kik.android"));
        
        // Additional Dating Apps
        iconList.add(new IconItem("Bumble", R.drawable.bumble, "com.bumble.app"));
        
        // Weather App
        iconList.add(new IconItem("Weather", R.drawable.weather, "com.weather.Weather"));
        
        // Additional Streaming Services
        iconList.add(new IconItem("Paramount+", R.drawable.paramount_plus, "com.cbs.app"));
        iconList.add(new IconItem("HBO Max", R.drawable.hbo_max, "com.hbo.hbonow"));
        iconList.add(new IconItem("Peacock", R.drawable.peacock, "com.peacocktv.peacockandroid"));
        
        // Creative & Design Tools
        iconList.add(new IconItem("Adobe Creative Cloud", R.drawable.adobe_creative_cloud, "com.adobe.creativeapps.cc"));
        iconList.add(new IconItem("Canva", R.drawable.canva, "com.canva.editor"));
        
        // Professional & Productivity Apps
        iconList.add(new IconItem("Shopify", R.drawable.shopify, "com.shopify.mobile"));
        iconList.add(new IconItem("WordPress", R.drawable.wordpress, "org.wordpress.android"));
        iconList.add(new IconItem("Evernote", R.drawable.evernote, "com.evernote"));
        iconList.add(new IconItem("Todoist", R.drawable.todoist, "com.todoist"));
        iconList.add(new IconItem("LastPass", R.drawable.lastpass, "com.lastpass.lpandroid"));
        
        // Travel & Navigation Apps
        iconList.add(new IconItem("TripAdvisor", R.drawable.tripadvisor, "com.tripadvisor.tripadvisor"));
        iconList.add(new IconItem("KAYAK", R.drawable.kayak, "com.kayak.android"));
        iconList.add(new IconItem("Expedia", R.drawable.expedia, "com.expedia.bookings"));
        
        // Utility Apps
        iconList.add(new IconItem("Flashlight", R.drawable.flashlight, "com.flashlight"));
        iconList.add(new IconItem("Compass", R.drawable.compass, "com.compass.android"));
        
        // Music Streaming Apps
        iconList.add(new IconItem("SoundCloud", R.drawable.soundcloud, "com.soundcloud.android"));
        iconList.add(new IconItem("Pandora", R.drawable.pandora, "com.pandora.android"));
        iconList.add(new IconItem("Deezer", R.drawable.deezer, "deezer.android.app"));
        
        // Additional Social Media Apps
        iconList.add(new IconItem("Mastodon", R.drawable.mastodon, "org.joinmastodon.android"));
        iconList.add(new IconItem("Threads", R.drawable.threads, "com.instagram.threads"));
        
        // Fast Food & Restaurant Apps
        iconList.add(new IconItem("McDonald's", R.drawable.mcdonalds, "com.mcdonalds.app"));
        iconList.add(new IconItem("Domino's Pizza", R.drawable.dominos_pizza, "com.dominos.android"));
        iconList.add(new IconItem("Chipotle", R.drawable.chipotle, "com.chipotle.ordering"));
        
        // Retail Shopping Apps
        iconList.add(new IconItem("Target", R.drawable.target, "com.target.ui"));
        iconList.add(new IconItem("Walmart", R.drawable.walmart, "com.walmart.android"));
        
        // Additional Android Apps
        iconList.add(new IconItem("Photos", R.drawable.photos, "com.google.android.apps.photos"));
        iconList.add(new IconItem("Recorder", R.drawable.recorder, "com.google.android.soundrecorder"));
        iconList.add(new IconItem("Sleep", R.drawable.sleep, "com.urbandroid.sleep"));
        iconList.add(new IconItem("Safety", R.drawable.safety, "com.google.android.apps.safetyhub"));
        iconList.add(new IconItem("Ruler", R.drawable.ruler, "com.google.android.apps.ruler"));
        iconList.add(new IconItem("Calendar", R.drawable.calendar, "com.google.android.calendar"));
        iconList.add(new IconItem("Pixel Tips", R.drawable.pixel_tips, "com.google.android.apps.tips"));
        iconList.add(new IconItem("Reading Mode", R.drawable.reading_mode, "com.google.android.accessibility.reader"));
        iconList.add(new IconItem("Watch", R.drawable.watch, "com.google.android.wearable.app"));
        
        // Popular Apps from Screenshots
        iconList.add(new IconItem("TickTick", R.drawable.ticktick, "com.ticktick.task"));
        iconList.add(new IconItem("Binance", R.drawable.binance, "com.binance.dev"));
        iconList.add(new IconItem("Authenticator", R.drawable.authenticator, "com.google.android.apps.authenticator2"));
        iconList.add(new IconItem("Truecaller", R.drawable.truecaller, "com.truecaller"));
        iconList.add(new IconItem("Translate", R.drawable.translate, "com.google.android.apps.translate"));
        iconList.add(new IconItem("Vezeeta", R.drawable.vezeeta, "com.vezeeta.patient"));
        iconList.add(new IconItem("Niagara Launcher", R.drawable.niagara_launcher, "bitpit.launcher"));
        iconList.add(new IconItem("Obsidian", R.drawable.obsidian, "md.obsidian"));
        iconList.add(new IconItem("Pixel IMS", R.drawable.pixel_ims, "com.shannon.imsservice"));
        iconList.add(new IconItem("Shizuku", R.drawable.shizuku, "moe.shizuku.privileged.api"));
        iconList.add(new IconItem("Xiaomi Home", R.drawable.xiaomi_home, "com.xiaomi.smarthome"));
        iconList.add(new IconItem("Aves", R.drawable.aves, "deckers.thibault.aves"));
        iconList.add(new IconItem("Wear Installer", R.drawable.wear_installer, "com.google.android.wearable.installer"));
        iconList.add(new IconItem("Notta", R.drawable.notta, "com.langogo.notta"));
        
        // Other Popular Apps
        iconList.add(new IconItem("Amazon", R.drawable.amazon, "com.amazon.mShop.android.shopping"));
    }
    
    private void checkLauncherCompatibility() {
        List<String> supportedLaunchers = getSupportedLaunchers();
        if (supportedLaunchers.isEmpty()) {
            Toast.makeText(this, "No compatible launcher found. You can still apply manually.", 
                         Toast.LENGTH_LONG).show();
            // Don't disable the button - allow manual application
            if (applyButton != null) {
                applyButton.setEnabled(true);
            }
        } else {
            String launcherText = "Compatible with: " + String.join(", ", supportedLaunchers);
            Toast.makeText(this, launcherText, Toast.LENGTH_SHORT).show();
            if (applyButton != null) {
                applyButton.setEnabled(true);
            }
        }
    }
    
    private List<String> getSupportedLaunchers() {
        List<String> launchers = new ArrayList<>();
        PackageManager pm = getPackageManager();
        
        // Check for Nova Launcher
        if (isPackageInstalled("com.teslacoilsw.launcher", pm)) {
            launchers.add("Nova Launcher");
        }
        
        // Check for Action Launcher
        if (isPackageInstalled("com.actionlauncher.playstore", pm)) {
            launchers.add("Action Launcher");
        }
        
        // Check for Apex Launcher
        if (isPackageInstalled("com.anddoes.launcher", pm)) {
            launchers.add("Apex Launcher");
        }
        
        // Check for Smart Launcher
        if (isPackageInstalled("ginlemon.flowerfree", pm)) {
            launchers.add("Smart Launcher");
        }
        
        return launchers;
    }
    
    private boolean isPackageInstalled(String packageName, PackageManager pm) {
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
    
    private void showApplyDialog() {
        ApplyDialogFragment dialog = new ApplyDialogFragment();
        dialog.show(getSupportFragmentManager(), "apply_dialog");
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        // Refresh launcher compatibility check
        checkLauncherCompatibility();
    }
}

