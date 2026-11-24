package smartpark;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

final class ParkingLot {
    static final int CAPACITY = 20;
    private final Visit[] slots = new Visit[CAPACITY];

    enum VehicleType {
        CAR("Car", "30.00"), MOTORCYCLE("Motorcycle", "20.00");

        private final String label;
        private final BigDecimal fee;

        VehicleType(String label, String fee) {
            this.label = label;
            this.fee = new BigDecimal(fee);
        }

        BigDecimal fee() {
            return fee;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    record Visit(String owner, String plate, VehicleType type, int slot, Instant timeIn) {}

    record Receipt(Visit visit, Instant timeOut, BigDecimal received, BigDecimal change) {}

    List<Visit> parkedVehicles() {
        return Arrays.stream(slots).filter(Objects::nonNull).toList();
    }

    int available() {
        int count = 0;
        for (Visit visit : slots) {
            if (visit == null) {
                count++;
            }
        }
        return count;
    }

    Visit find(String plate) {
        String key = plateKey(plate);
        for (Visit visit : slots) {
            if (visit != null && plateKey(visit.plate()).equals(key)) {
                return visit;
            }
        }
        return null;
    }

    Visit park(String owner, String plate, VehicleType type) {
        if (owner.isBlank() || plateKey(plate).isEmpty() || type == null) {
            throw new IllegalArgumentException("Enter the owner and plate number, and select a vehicle type.");
        }
        if (find(plate) != null) {
            throw new IllegalArgumentException("That plate is already parked.");
        }
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null) {
                Visit visit = new Visit(owner.strip(), plate.strip().toUpperCase(Locale.ROOT),
                        type, i + 1, Instant.now());
                slots[i] = visit;
                return visit;
            }
        }
        throw new IllegalArgumentException("Parking is full. Check out a vehicle to free a slot.");
    }

    Receipt checkout(Visit visit, BigDecimal received) {
        int index = visit.slot() - 1;
        if (slots[index] != visit) {
            throw new IllegalArgumentException("This vehicle is no longer parked.");
        }
        BigDecimal change = received.subtract(visit.type().fee());
        if (change.signum() < 0) {
            throw new IllegalArgumentException("Cash received must cover the parking fee.");
        }
        Instant timeOut = Instant.now();
        if (timeOut.isBefore(visit.timeIn())) {
            timeOut = visit.timeIn();
        }
        Receipt receipt = new Receipt(visit, timeOut, received, change);
        slots[index] = null;
        return receipt;
    }

    private static String plateKey(String plate) {
        return plate.replaceAll("(?U)\\s+", "").toUpperCase(Locale.ROOT);
    }
}
