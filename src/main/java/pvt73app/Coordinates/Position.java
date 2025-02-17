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

public abstract class Position {

    public enum Grid {RT90,WGS84,SWEREF99}

    protected double latitude;
    protected double longitude;
    protected Grid gridFormat;

    public Position(double lat, double lon,Grid format) {
        latitude = lat;
        longitude = lon;
        gridFormat = format;
    }
    public Position(Grid format) {
        gridFormat = format;
    }
    public Position() {
        
    }

    public double getLatitude() {
        return this.latitude;
    }
    public double getLongitude() {
        return this.longitude;
    }
}