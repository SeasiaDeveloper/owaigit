package com.oway.model.response;

import java.util.List;

public class GetRecommendedPlacesResponse {


    private List<?> html_attributions;
    private List<ResultsBean> results;

    public List<?> getHtml_attributions() {
        return html_attributions;
    }

    public void setHtml_attributions(List<?> html_attributions) {
        this.html_attributions = html_attributions;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 5d8b35c1c68fe91e2201ee7c
         * formatted_address :
         * geometry : {"location":{"lat":30.71993399999999,"lng":76.729232},"viewport":{"northeast":{"lat":30.72133418029149,"lng":76.73065573029152},"southwest":{"lat":30.71863621970849,"lng":76.72795776970851}}}
         * icon : https://maps.gstatic.com/mapfiles/place_api/icons/lodging-71.png
         * id : 3c6a16ce6c4896d1b968f32cc440f3fa176d12b0
         * name : Hotel City Plaza 3
         * photos : [{"height":1468,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/102527250498203241571/photos\">Hotel City Plaza 3<\/a>"],"photo_reference":"CmRaAAAAFBkhwAi_AhMnaocH8o1O7D9NbIglgtFpLy_jNfY81730p79ax5BuWcWWyvhE6Q2mcNar_Hfwjl3cQnALlGHQKCqC5CtFwlBM6DNNjrzBwgL8qqD_lVBT5-pAfvPAw3I8EhBhai53KZtvfgFDXT6bJTRoGhTtNLS9_-I9z_NVYzrlbiYuWx6a9w","width":1102}]
         * place_id : ChIJq6rqCdjtDzkR0SxgsUrkNDg
         * reference : ChIJq6rqCdjtDzkR0SxgsUrkNDg
         * types : ["lodging","point_of_interest","establishment"]
         * loc : {"type":"Point","coordinates":[76.729232,30.71993399999999]}
         * updated_at : 2019-09-25 16:39:13
         * created_at : 2019-09-25 16:39:13
         */

        private String _id;
        private String formatted_address;
        private GeometryBean geometry;
        private String icon;
        private String id;
        private String name;
        private String place_id;
        private String reference;
        private LocBean loc;
        private String updated_at;
        private String created_at;
        private List<PhotosBean> photos;
        private List<String> types;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public GeometryBean getGeometry() {
            return geometry;
        }

        public void setGeometry(GeometryBean geometry) {
            this.geometry = geometry;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlace_id() {
            return place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public LocBean getLoc() {
            return loc;
        }

        public void setLoc(LocBean loc) {
            this.loc = loc;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public List<PhotosBean> getPhotos() {
            return photos;
        }

        public void setPhotos(List<PhotosBean> photos) {
            this.photos = photos;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public static class GeometryBean {
            /**
             * location : {"lat":30.71993399999999,"lng":76.729232}
             * viewport : {"northeast":{"lat":30.72133418029149,"lng":76.73065573029152},"southwest":{"lat":30.71863621970849,"lng":76.72795776970851}}
             */

            private LocationBean location;
            private ViewportBean viewport;

            public LocationBean getLocation() {
                return location;
            }

            public void setLocation(LocationBean location) {
                this.location = location;
            }

            public ViewportBean getViewport() {
                return viewport;
            }

            public void setViewport(ViewportBean viewport) {
                this.viewport = viewport;
            }

            public static class LocationBean {
                /**
                 * lat : 30.71993399999999
                 * lng : 76.729232
                 */

                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }

            public static class ViewportBean {
                /**
                 * northeast : {"lat":30.72133418029149,"lng":76.73065573029152}
                 * southwest : {"lat":30.71863621970849,"lng":76.72795776970851}
                 */

                private NortheastBean northeast;
                private SouthwestBean southwest;

                public NortheastBean getNortheast() {
                    return northeast;
                }

                public void setNortheast(NortheastBean northeast) {
                    this.northeast = northeast;
                }

                public SouthwestBean getSouthwest() {
                    return southwest;
                }

                public void setSouthwest(SouthwestBean southwest) {
                    this.southwest = southwest;
                }

                public static class NortheastBean {
                    /**
                     * lat : 30.72133418029149
                     * lng : 76.73065573029152
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }

                public static class SouthwestBean {
                    /**
                     * lat : 30.71863621970849
                     * lng : 76.72795776970851
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }
            }
        }

        public static class LocBean {
            /**
             * type : Point
             * coordinates : [76.729232,30.71993399999999]
             */

            private String type;
            private List<Double> coordinates;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<Double> getCoordinates() {
                return coordinates;
            }

            public void setCoordinates(List<Double> coordinates) {
                this.coordinates = coordinates;
            }
        }

        public static class PhotosBean {
            /**
             * height : 1468
             * html_attributions : ["<a href=\"https://maps.google.com/maps/contrib/102527250498203241571/photos\">Hotel City Plaza 3<\/a>"]
             * photo_reference : CmRaAAAAFBkhwAi_AhMnaocH8o1O7D9NbIglgtFpLy_jNfY81730p79ax5BuWcWWyvhE6Q2mcNar_Hfwjl3cQnALlGHQKCqC5CtFwlBM6DNNjrzBwgL8qqD_lVBT5-pAfvPAw3I8EhBhai53KZtvfgFDXT6bJTRoGhTtNLS9_-I9z_NVYzrlbiYuWx6a9w
             * width : 1102
             */

            private int height;
            private String photo_reference;
            private int width;
            private List<String> html_attributions;

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public String getPhoto_reference() {
                return photo_reference;
            }

            public void setPhoto_reference(String photo_reference) {
                this.photo_reference = photo_reference;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public List<String> getHtml_attributions() {
                return html_attributions;
            }

            public void setHtml_attributions(List<String> html_attributions) {
                this.html_attributions = html_attributions;
            }
        }
    }
}