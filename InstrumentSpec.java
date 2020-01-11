import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class InstrumentSpec {
    private Map<String, Object> properties;

    public InstrumentSpec(Map<String, Object> properties) {
        if (properties == null) {
            this.properties = new HashMap<>();
        } else {
            this.properties = new HashMap<>(properties);
        }
    }

    public Object getProperty(String propertyName) {
        return properties.get(propertyName);
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public boolean matches(InstrumentSpec otherSpec) {
        for (Iterator<String> i = otherSpec.getProperties().keySet().iterator(); i.hasNext(); ) {
            String propertyName = i.next();
            if (!properties.get(propertyName).equals(otherSpec.getProperty(propertyName))) {
                return false;
            }
        }
        return true;
    }
}