/*
 * Copyright (C) 2012 Goober <http://www.github.com/goober>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pvt73app.Coordinates;

public class GaussKreuger {

    /**
     * Semi-major axis of the ellipsoid.
     */
    private double axis;

    /**
     * Flattening of the ellipsoid.
     */
    private double flattening;

    /**
     * Central meridian for the projection.
     */
    private double central_meridian;

    /**
     * Scale on central meridian.
     */
    private double scale;

    /**
     * Offset for origo.
     */
    private double false_northing;

    /**
     * Offset for origo.
     */
    private double false_easting;

    /**
     * Parameters for RT90 and SWEREF99TM.
     * Note: Parameters for RT90 are choosen to eliminate the
     * differences between Bessel and GRS80-ellipsoides.
     * Bessel-variants should only be used if lat/long are given as
     * RT90-lat/long based on the Bessel ellipsoide (from old maps).
     * @param projection
     */
    public void swedish_params(String projection) {
        // RT90 parameters, GRS 80 ellipsoid.
        if (projection.equals("rt90_7.5_gon_v")) {
            grs80_params();
            central_meridian = 11.0 + 18.375 / 60.0;
            scale = 1.000006000000;
            false_northing = -667.282;
            false_easting = 1500025.141;
        } else if (projection.equals("rt90_5.0_gon_v")) {
            grs80_params();
            central_meridian = 13.0 + 33.376 / 60.0;
            scale = 1.000005800000;
            false_northing = -667.130;
            false_easting = 1500044.695;
        } else if (projection.equals("rt90_2.5_gon_v")) {
            grs80_params();
            central_meridian = 15.0 + 48.0 / 60.0 + 22.624306 / 3600.0;
            scale = 1.00000561024;
            false_northing = -667.711;
            false_easting = 1500064.274;
        } else if (projection.equals("rt90_0.0_gon_v")) {
            grs80_params();
            central_meridian = 18.0 + 3.378 / 60.0;
            scale = 1.000005400000;
            false_northing = -668.844;
            false_easting = 1500083.521;
        } else if (projection.equals("rt90_2.5_gon_o")) {
            grs80_params();
            central_meridian = 20.0 + 18.379 / 60.0;
            scale = 1.000005200000;
            false_northing = -670.706;
            false_easting = 1500102.765;
        } else if (projection.equals("rt90_5.0_gon_o")) {
            grs80_params();
            central_meridian = 22.0 + 33.380 / 60.0;
            scale = 1.000004900000;
            false_northing = -672.557;
            false_easting = 1500121.846;
        } // RT90 parameters, Bessel 1841 ellipsoid.
        else if (projection.equals("bessel_rt90_7.5_gon_v")) {
            bessel_params();
            central_meridian = 11.0 + 18.0 / 60.0 + 29.8 / 3600.0;
        } else if (projection.equals("bessel_rt90_5.0_gon_v")) {
            bessel_params();
            central_meridian = 13.0 + 33.0 / 60.0 + 29.8 / 3600.0;
        } else if (projection.equals("bessel_rt90_2.5_gon_v")) {
            bessel_params();
            central_meridian = 15.0 + 48.0 / 60.0 + 29.8 / 3600.0;
        } else if (projection.equals("bessel_rt90_0.0_gon_v")) {
            bessel_params();
            central_meridian = 18.0 + 3.0 / 60.0 + 29.8 / 3600.0;
        } else if (projection.equals("bessel_rt90_2.5_gon_o")) {
            bessel_params();
            central_meridian = 20.0 + 18.0 / 60.0 + 29.8 / 3600.0;
        } else if (projection.equals("bessel_rt90_5.0_gon_o")) {
            bessel_params();
            central_meridian = 22.0 + 33.0 / 60.0 + 29.8 / 3600.0;
        } // SWEREF99TM and SWEREF99ddmm  parameters.
        else if (projection.equals("sweref_99_tm")) {
            sweref99_params();
            central_meridian = 15.00;
            scale = 0.9996;
            false_northing = 0.0;
            false_easting = 500000.0;
        } else if (projection.equals("sweref_99_1200")) {
            sweref99_params();
            central_meridian = 12.00;
        } else if (projection.equals("sweref_99_1330")) {
            sweref99_params();
            central_meridian = 13.50;
        } else if (projection.equals("sweref_99_1500")) {
            sweref99_params();
            central_meridian = 15.00;
        } else if (projection.equals("sweref_99_1630")) {
            sweref99_params();
            central_meridian = 16.50;
        } else if (projection.equals("sweref_99_1800")) {
            sweref99_params();
            central_meridian = 18.00;
        } else if (projection.equals("sweref_99_1415")) {
            sweref99_params();
            central_meridian = 14.25;
        } else if (projection.equals("sweref_99_1545")) {
            sweref99_params();
            central_meridian = 15.75;
        } else if (projection.equals("sweref_99_1715")) {
            sweref99_params();
            central_meridian = 17.25;
        } else if (projection.equals("sweref_99_1845")) {
            sweref99_params();
            central_meridian = 18.75;
        } else if (projection.equals("sweref_99_2015")) {
            sweref99_params();
            central_meridian = 20.25;
        } else if (projection.equals("sweref_99_2145")) {
            sweref99_params();
            central_meridian = 21.75;
        } else if (projection.equals("sweref_99_2315")) {
            sweref99_params();
            central_meridian = 23.25;
        } else {
            central_meridian = Double.MIN_VALUE;
        }
    }

    // Sets of default parameters.
    private void grs80_params() {
        axis = 6378137.0; // GRS 80.
        flattening = 1.0 / 298.257222101; // GRS 80.
        central_meridian = Double.MIN_VALUE;
    }

    private void bessel_params() {
        axis = 6377397.155; // Bessel 1841.
        flattening = 1.0 / 299.1528128; // Bessel 1841.
        central_meridian = Double.MIN_VALUE;
        scale = 1.0;
        false_northing = 0.0;
        false_easting = 1500000.0;
    }

    private void sweref99_params() {
        axis = 6378137.0; // GRS 80.
        flattening = 1.0 / 298.257222101; // GRS 80.
        central_meridian = Double.MIN_VALUE;
        scale = 1.0;
        false_northing = 0.0;
        false_easting = 150000.0;
    }

    // Conversion from geodetic coordinates to grid coordinates.
    public double[] geodetic_to_grid(double latitude, double longitude) {
        double[] x_y = new double[2];

        // Prepare ellipsoid-based stuff.
        double e2 = flattening * (2.0 - flattening);
        double n = flattening / (2.0 - flattening);
        double a_roof = axis / (1.0 + n) * (1.0 + n * n / 4.0 + n * n * n * n / 64.0);
        double A = e2;
        double B = (5.0 * e2 * e2 - e2 * e2 * e2) / 6.0;
        double C = (104.0 * e2 * e2 * e2 - 45.0 * e2 * e2 * e2 * e2) / 120.0;
        double D = (1237.0 * e2 * e2 * e2 * e2) / 1260.0;
        double beta1 = n / 2.0 - 2.0 * n * n / 3.0 + 5.0 * n * n * n / 16.0 + 41.0 * n * n * n * n / 180.0;
        double beta2 = 13.0 * n * n / 48.0 - 3.0 * n * n * n / 5.0 + 557.0 * n * n * n * n / 1440.0;
        double beta3 = 61.0 * n * n * n / 240.0 - 103.0 * n * n * n * n / 140.0;
        double beta4 = 49561.0 * n * n * n * n / 161280.0;

        // Convert.
        double deg_to_rad = Math.PI / 180.0;
        double phi = latitude * deg_to_rad;
        double lambda = longitude * deg_to_rad;
        double lambda_zero = central_meridian * deg_to_rad;

        double phi_star = phi - Math.sin(phi) * Math.cos(phi) * (A +
                B * Math.pow(Math.sin(phi), 2) +
                C * Math.pow(Math.sin(phi), 4) +
                D * Math.pow(Math.sin(phi), 6));
        double delta_lambda = lambda - lambda_zero;
        double xi_prim = Math.atan(Math.tan(phi_star) / Math.cos(delta_lambda));
        double eta_prim = math_atanh(Math.cos(phi_star) * Math.sin(delta_lambda));
        double x = scale * a_roof * (xi_prim +
                beta1 * Math.sin(2.0 * xi_prim) * math_cosh(2.0 * eta_prim) +
                beta2 * Math.sin(4.0 * xi_prim) * math_cosh(4.0 * eta_prim) +
                beta3 * Math.sin(6.0 * xi_prim) * math_cosh(6.0 * eta_prim) +
                beta4 * Math.sin(8.0 * xi_prim) * math_cosh(8.0 * eta_prim)) +
                false_northing;
        double y = scale * a_roof * (eta_prim +
                beta1 * Math.cos(2.0 * xi_prim) * math_sinh(2.0 * eta_prim) +
                beta2 * Math.cos(4.0 * xi_prim) * math_sinh(4.0 * eta_prim) +
                beta3 * Math.cos(6.0 * xi_prim) * math_sinh(6.0 * eta_prim) +
                beta4 * Math.cos(8.0 * xi_prim) * math_sinh(8.0 * eta_prim)) +
                false_easting;
        x_y[0] = Math.round(x * 1000.0) / 1000.0;
        x_y[1] = Math.round(y * 1000.0) / 1000.0;

        return x_y;
    }

    // Conversion from grid coordinates to geodetic coordinates.
    public double[] grid_to_geodetic(double x, double y) {
        double[] lat_lon = new double[2];
        if (central_meridian == Double.MIN_VALUE) {
            return lat_lon;
        }
        // Prepare ellipsoid-based stuff.
        double e2 = flattening * (2.0 - flattening);
        double n = flattening / (2.0 - flattening);
        double a_roof = axis / (1.0 + n) * (1.0 + n * n / 4.0 + n * n * n * n / 64.0);
        double delta1 = n / 2.0 - 2.0 * n * n / 3.0 + 37.0 * n * n * n / 96.0 - n * n * n * n / 360.0;
        double delta2 = n * n / 48.0 + n * n * n / 15.0 - 437.0 * n * n * n * n / 1440.0;
        double delta3 = 17.0 * n * n * n / 480.0 - 37 * n * n * n * n / 840.0;
        double delta4 = 4397.0 * n * n * n * n / 161280.0;

        double Astar = e2 + e2 * e2 + e2 * e2 * e2 + e2 * e2 * e2 * e2;
        double Bstar = -(7.0 * e2 * e2 + 17.0 * e2 * e2 * e2 + 30.0 * e2 * e2 * e2 * e2) / 6.0;
        double Cstar = (224.0 * e2 * e2 * e2 + 889.0 * e2 * e2 * e2 * e2) / 120.0;
        double Dstar = -(4279.0 * e2 * e2 * e2 * e2) / 1260.0;

        // Convert.
        double deg_to_rad = Math.PI / 180;
        double lambda_zero = central_meridian * deg_to_rad;
        double xi = (x - false_northing) / (scale * a_roof);
        double eta = (y - false_easting) / (scale * a_roof);
        double xi_prim = xi -
                delta1 * Math.sin(2.0 * xi) * math_cosh(2.0 * eta) -
                delta2 * Math.sin(4.0 * xi) * math_cosh(4.0 * eta) -
                delta3 * Math.sin(6.0 * xi) * math_cosh(6.0 * eta) -
                delta4 * Math.sin(8.0 * xi) * math_cosh(8.0 * eta);
        double eta_prim = eta -
                delta1 * Math.cos(2.0 * xi) * math_sinh(2.0 * eta) -
                delta2 * Math.cos(4.0 * xi) * math_sinh(4.0 * eta) -
                delta3 * Math.cos(6.0 * xi) * math_sinh(6.0 * eta) -
                delta4 * Math.cos(8.0 * xi) * math_sinh(8.0 * eta);
        double phi_star = Math.asin(Math.sin(xi_prim) / math_cosh(eta_prim));
        double delta_lambda = Math.atan(math_sinh(eta_prim) / Math.cos(xi_prim));
        double lon_radian = lambda_zero + delta_lambda;
        double lat_radian = phi_star + Math.sin(phi_star) * Math.cos(phi_star) *
                (Astar +
                Bstar * Math.pow(Math.sin(phi_star), 2) +
                Cstar * Math.pow(Math.sin(phi_star), 4) +
                Dstar * Math.pow(Math.sin(phi_star), 6));
        lat_lon[0] = lat_radian * 180.0 / Math.PI;
        lat_lon[1] = lon_radian * 180.0 / Math.PI;
        return lat_lon;
    }

    private double math_sinh(double value) {
        return 0.5 * (Math.exp(value) - Math.exp(-value));
    }

    private double math_cosh(double value) {
        return 0.5 * (Math.exp(value) + Math.exp(-value));
    }

    private double math_atanh(double value) {
        return 0.5 * Math.log((1.0 + value) / (1.0 - value));
    }
}