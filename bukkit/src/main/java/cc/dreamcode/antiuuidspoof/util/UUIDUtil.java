package cc.dreamcode.antiuuidspoof.util;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@UtilityClass
public class UUIDUtil {

    public static UUID generateUUIDFromName(String name) {
        return UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes());
    }

}
